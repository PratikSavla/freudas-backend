package com.freudas.dev.service;

import com.freudas.dev.exception.BadRequestException;
import com.freudas.dev.exception.ResourceNotFoundException;
import com.freudas.dev.model.*;
import com.freudas.dev.repository.WorkQuestionTagRepository;
import com.freudas.dev.repository.UserRepository;
import com.freudas.dev.repository.WorkProviderRepository;
import com.freudas.dev.repository.WorkUserQuestionAnswerRepository;
import com.freudas.dev.util.AnalysisUtils;
import com.freudas.dev.util.Constants;
import com.freudas.dev.util.HelperFunctions;
import com.freudas.dev.viewmodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WorkProviderService implements IWorkProviderService{

    final private WorkProviderRepository workProviderRepository;
    final private UserRepository userRepository;
    final private WorkUserQuestionAnswerRepository workUserQuestionAnswerRepository;
    final private WorkQuestionTagRepository tagRepository;

    public WorkProviderService(WorkProviderRepository workProviderRepository, UserRepository userRepository, WorkUserQuestionAnswerRepository workUserQuestionAnswerRepository, WorkQuestionTagRepository tagRepository) {
        this.workProviderRepository = workProviderRepository;
        this.userRepository = userRepository;
        this.workUserQuestionAnswerRepository = workUserQuestionAnswerRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public ApiResponse addWorkProvider(RegisterWorkProviderViewModel viewModel) {
        if(userRepository.existsByEmail(viewModel.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }
        // Creating user's account
        User user = new User();
        user.setName(viewModel.getName());
        user.setEmail(viewModel.getEmail());
        user.setProvider(AuthProvider.local);
        user.setRole(Constants.WORK_OWNER_ROLE);
        user.setAdminBlocked(false);
        user.setLoginBlocked(true);
        user.setEmailVerified(false);
        String resetKey = HelperFunctions.generateRandomString(8);
        user.setResetKey(resetKey);
        User result = userRepository.save(user);

        WorkProvider workProvider = new WorkProvider();
        workProvider.setProvider(viewModel.getProvider());
        workProvider.setDomain(viewModel.getDomain());
        workProvider.setCompanyName(viewModel.getCompanyName());
        workProvider.setLoginURL(viewModel.getLoginURL());
        workProvider.setOwner(result);
        this.workProviderRepository.save(workProvider);

        return new ApiResponse(true, resetKey);
    }

    @Override
    public WorkProviderResponseViewModel getProvider(String email) {
        String domain = email.substring(email.indexOf("@") + 1);
        WorkProvider provider = this.workProviderRepository.findByDomain(domain).orElseThrow(() -> new BadRequestException("Invalid email"));
        WorkProviderResponseViewModel viewModel = new WorkProviderResponseViewModel();
        viewModel.setProvider(provider.getProvider());
        viewModel.setCompanyName(provider.getCompanyName());
        viewModel.setLoginURL(provider.getLoginURL());
        return viewModel;
    }

    @Override
    public ProviderAnalysisViewModel getUsersAnalysis(long providerOwnerId, String fromDate, String toDate) {
        WorkProvider provider = this.findProviderByOwnerId(providerOwnerId);
        LocalDateTime startDateTime = LocalDateTime.parse(fromDate, DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime endDateTime = LocalDateTime.parse(toDate, DateTimeFormatter.ISO_DATE_TIME);
        return new ProviderAnalysisViewModel(
                AnalysisUtils.getAnalysis(this.tagRepository.findAll(),
                        this.workUserQuestionAnswerRepository.getAnswersForUsersBetweenDates(
                                startDateTime,
                                endDateTime,
                                this.userRepository.findAllByProvider(AuthProvider.valueOf(provider.getProvider())))),
                endDateTime, startDateTime);
    }

    @Override
    public ApiResponse addPreferredTag(List<Long> tagIds, long providerOwnerId) {
        WorkProvider provider = this.findProviderByOwnerId(providerOwnerId);
        tagIds.forEach(tagId -> {
            WorkQuestionTag tag = this.tagRepository.findById(tagId).orElseThrow(() -> new ResourceNotFoundException("QuestionTag", "id", tagId));
            provider.getPreferedTags().add(tag);
        });
        this.workProviderRepository.saveAndFlush(provider);
        return new ApiResponse(true, "Preferred tags added!");
    }

    @Override
    public ApiResponse removePreferredTag(long tagId, long providerOwnerId) {
        WorkProvider provider = this.findProviderByOwnerId(providerOwnerId);
        provider.getPreferedTags().removeIf(questionTag -> questionTag.getId() == tagId);
        this.workProviderRepository.saveAndFlush(provider);
        return new ApiResponse(true, "Tag removed!");
    }

    @Override
    public ProviderDetailsViewModel getProviderDetails(long providerOwnerId) {
        WorkProvider provider = this.findProviderByOwnerId(providerOwnerId);
        ProviderDetailsViewModel viewModel = new ProviderDetailsViewModel();
        viewModel.setId(provider.getId());
        viewModel.setProvider(provider.getProvider());
        viewModel.setDomain(provider.getDomain());
        viewModel.setPreferredTags(provider.getPreferedTags()
                .stream().map(questionTag -> new WorkQuestionTagViewModel(questionTag.getId(), questionTag.getTag()))
                .collect(Collectors.toList()));
        viewModel.setCompanyName(provider.getCompanyName());
        viewModel.setLoginURL(provider.getLoginURL());
        viewModel.setOwnerEmail(provider.getOwner().getEmail());
        viewModel.setOwnerName(provider.getOwner().getName());
        viewModel.setOwnerImageUrl(provider.getOwner().getImageUrl());
        return viewModel;
    }

    private WorkProvider findProviderByOwnerId(long ownerId) {
        User user = this.userRepository.findById(ownerId).orElseThrow(() -> new BadRequestException("Invalid owner id"));
        return this.workProviderRepository.findByOwner(user);
    }
}
