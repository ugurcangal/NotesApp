package com.cangaldev.notesapp.Repository

import androidx.lifecycle.LiveData
import com.cangaldev.notesapp.Dao.NotesDao
import com.cangaldev.notesapp.model.Notes

class NotesRepository(val dao: NotesDao) {

    fun getallNotes():LiveData<List<Notes>> = dao.getNotes()


    fun getLowNotes():LiveData<List<Notes>> = dao.getLowNotes()
    fun getHighNotes():LiveData<List<Notes>> = dao.getHighNotes()
    fun getMediumNotes():LiveData<List<Notes>> = dao.getMediumNotes()

    fun insertNotes(notes: Notes){

        dao.insertNotes(notes)

    }

    fun deleteNotes(id:Int){
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }

}