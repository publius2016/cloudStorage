package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.service.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/note")
public class NoteController {
    private NoteService noteService;
    private CredentialService credentialService;
    private AuthenticationService authenticationService;

    public NoteController(NoteService noteService, AuthenticationService authenticationService, CredentialService credentialService) {
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public String addNote(NoteForm noteForm, CredentialForm credentialForm, Model model) {
        if (noteForm.getNoteId() != null) {
            noteService.updateNote(noteForm);
        } else {
            noteService.addNote(noteForm);
        }

        model.addAttribute("notes", noteService.getNotes(authenticationService.getCurrentUsername()));
        model.addAttribute("credentials", credentialService.getCredentials(authenticationService.getCurrentUsername()));
        return "home";
    }

    @GetMapping("/delete/{noteId}")
    public String deleteNote(NoteForm noteForm, CredentialForm credentialForm, @PathVariable("noteId") String noteId, Model model) {
        noteService.deleteNote(noteId);
        model.addAttribute("notes", noteService.getNotes(authenticationService.getCurrentUsername()));
        model.addAttribute("credentials", credentialService.getCredentials(authenticationService.getCurrentUsername()));
        return "home";
    }
}
