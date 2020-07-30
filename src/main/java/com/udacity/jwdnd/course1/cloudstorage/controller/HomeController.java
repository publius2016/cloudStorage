package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.service.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    private NoteService noteService;
    private AuthenticationService authenticationService;
    private CredentialService credentialService;
    private FileService fileService;

    public HomeController(NoteService noteService, AuthenticationService authenticationService, CredentialService credentialService, FileService fileService) {
        this.noteService = noteService;
        this.authenticationService = authenticationService;
        this.credentialService = credentialService;
        this.fileService = fileService;
    }

    @GetMapping("/home")
    public String getHome(NoteForm noteForm, CredentialForm credentialForm, FileForm fileForm, Model model, @RequestParam(required=false) String message) {
        model.addAttribute("notes", noteService.getNotes(authenticationService.getCurrentUsername()));
        model.addAttribute("credentials", credentialService.getCredentials(authenticationService.getCurrentUsername()));
        model.addAttribute("files", fileService.getFileNames(authenticationService.getCurrentUsername()));
        model.addAttribute("message", message);
        return "home";
    }
}
