package controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import dao.NotesDao;
import model.Note;

public class NotesController {

    private NotesDao mychatapp_Ai_notesDao = new NotesDao();

    public void addNote(Note mychatapp_Ai_note) {
        try {
            mychatapp_Ai_notesDao.addData(
                    "notes",
                    String.valueOf(System.currentTimeMillis()),
                    mychatapp_Ai_note
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public List<Note> getAllNotesForUser(String mychatapp_Ai_userName) {
        try {
            return mychatapp_Ai_notesDao.getDataList("notes", mychatapp_Ai_userName);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return List.of();
    }
}
