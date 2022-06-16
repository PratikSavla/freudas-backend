package com.freudas.dev.controller;

import com.freudas.dev.security.CurrentUser;
import com.freudas.dev.security.UserPrincipal;
import com.freudas.dev.service.IWorkProviderService;
import com.freudas.dev.util.Constants;
import com.freudas.dev.viewmodel.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/work-provider")
public class WorkProviderController {
    final private IWorkProviderService workProviderService;

    public WorkProviderController(IWorkProviderService workProviderService) {
        this.workProviderService = workProviderService;
    }

    @GetMapping
    @PreAuthorize("hasRole('"+ Constants.WORK_OWNER_ROLE +"')")
    ResponseEntity<ProviderDetailsViewModel> getProviderDetails(@CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(this.workProviderService.getProviderDetails(userPrincipal.getId()));
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('"+ Constants.ADMIN_USER_ROLE +"')")
    ResponseEntity<ApiResponse> addWorkProvider(@RequestBody RegisterWorkProviderViewModel viewModel) {
        return ResponseEntity.ok(this.workProviderService.addWorkProvider(viewModel));
    }

    @GetMapping("/{email}")
    ResponseEntity<WorkProviderResponseViewModel> getProvider(@PathVariable String email) {
        return ResponseEntity.ok(this.workProviderService.getProvider(email));
    }

    @GetMapping("/analysis")
    @PreAuthorize("hasRole('"+ Constants.WORK_OWNER_ROLE +"')")
    ResponseEntity<ProviderAnalysisViewModel> getProvider(@CurrentUser UserPrincipal userPrincipal, @RequestParam String fromDate, @RequestParam String toDate) {
        return ResponseEntity.ok(this.workProviderService.getUsersAnalysis(userPrincipal.getId(), fromDate, toDate));
    }
    @PostMapping("/preferred-tags")
    @PreAuthorize("hasRole('"+ Constants.WORK_OWNER_ROLE +"')")
    ResponseEntity<ApiResponse> getProvider(@RequestBody List<Long> tagIds, @CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(this.workProviderService.addPreferredTag(tagIds, userPrincipal.getId()));
    }
    @DeleteMapping("/preferred-tags/{tagId}")
    @PreAuthorize("hasRole('"+ Constants.WORK_OWNER_ROLE +"')")
    ResponseEntity<ApiResponse> removePreferredTag(@PathVariable long tagId, @CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(this.workProviderService.removePreferredTag(tagId, userPrincipal.getId()));
    }
}
