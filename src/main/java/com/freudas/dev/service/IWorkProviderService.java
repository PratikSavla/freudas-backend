package com.freudas.dev.service;

import com.freudas.dev.viewmodel.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IWorkProviderService {
    ApiResponse addWorkProvider(RegisterWorkProviderViewModel viewModel);
    WorkProviderResponseViewModel getProvider(String email);
    ProviderAnalysisViewModel getUsersAnalysis(long providerOwnerId, String fromDate, String toDate);
    ApiResponse addPreferredTag(List<Long> tagIds, long providerOwnerId);
    ApiResponse removePreferredTag(long tagId, long providerOwnerId);
    ProviderDetailsViewModel getProviderDetails(long providerOwnerId);
}
