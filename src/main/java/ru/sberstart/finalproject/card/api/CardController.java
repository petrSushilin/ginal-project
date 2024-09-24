package ru.sberstart.finalproject.card.api;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberstart.finalproject.card.api.dto.inner.CardGeneralInformationDTO;
import ru.sberstart.finalproject.card.api.dto.request.CardIssueRequestDTO;
import ru.sberstart.finalproject.card.api.dto.request.CardStateRequestDTO;
import ru.sberstart.finalproject.card.api.dto.response.CardStateResponseDTO;
import ru.sberstart.finalproject.card.application.service.CardService;

import java.util.List;

@RequestMapping("/api/v1/cards")
@RestController
public class CardController {
    CardService service;

    public CardController(CardService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/new")
    public ResponseEntity<CardStateResponseDTO> issueCard(
            @Parameter(description = "DTO с информацией для выпуска карты для банковского счета", required = true)
            @RequestBody CardIssueRequestDTO cardIssueDTO) {
        CardStateResponseDTO issuedCard = service.issueCard(cardIssueDTO);
        return new ResponseEntity<>(issuedCard, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER', 'MANAGER')")
    @GetMapping("/{cardNumber}")
    public ResponseEntity<CardGeneralInformationDTO> getGeneralInformationByNumber(@PathVariable String cardNumber) {
        CardGeneralInformationDTO informationDTO = service.getGeneralInformationByNumber(cardNumber);
        return new ResponseEntity<>(informationDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER', 'MANAGER')")
    @GetMapping("/user:{userPhoneNumber}")
    public ResponseEntity<List<CardGeneralInformationDTO>> getListCardsByUserPhoneNumber(@PathVariable String userPhoneNumber) {
        List<CardGeneralInformationDTO> listCards = service.getListCardsByUserPhoneNumber(userPhoneNumber);
        return new ResponseEntity<>(listCards, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PatchMapping("/approve")
    public ResponseEntity<CardStateResponseDTO> approveCard(
            @Parameter(description = "DTO с информацией для подтверждения выпуска карты", required = true)
            @RequestBody CardStateRequestDTO cardPatchStateDTO) {
        CardStateResponseDTO approvedCard = service.approveCard(cardPatchStateDTO);
        return new ResponseEntity<>(approvedCard, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PatchMapping("/activate")
    public ResponseEntity<CardStateResponseDTO> activateCard(
            @Parameter(description = "DTO с информацией для подтверждения выдачи и активации карты", required = true)
            @RequestBody CardStateRequestDTO cardPatchStateDTO) {
        CardStateResponseDTO approvedCard = service.activateCard(cardPatchStateDTO);
        return new ResponseEntity<>(approvedCard, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER', 'MANAGER')")
    @PatchMapping("/suspend")
    public ResponseEntity<CardStateResponseDTO> suspendCard(
            @Parameter(description = "DTO с информацией для подтверждения приостановки", required = true)
            @RequestBody CardStateRequestDTO cardPatchStateDTO) {
        CardStateResponseDTO approvedCard = service.suspendCard(cardPatchStateDTO);
        return new ResponseEntity<>(approvedCard, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/close")
    public ResponseEntity<CardStateResponseDTO> blockExpiredCard(
            @Parameter(description = "DTO с информацией для подтверждения истечения срока действия карты", required = true)
            @RequestBody CardStateRequestDTO cardPatchStateDTO) {
        CardStateResponseDTO approvedCard = service.blockExpireCard(cardPatchStateDTO);
        return new ResponseEntity<>(approvedCard, HttpStatus.OK);
    }
}
