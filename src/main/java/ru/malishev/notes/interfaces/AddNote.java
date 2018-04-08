package ru.malishev.notes.interfaces;

import ru.malishev.notes.objects.Note;
/**
 * JavaFXMavenSQLite. "Notes"
 * @author Aleksey Malyshev
 * @version 1.0 dated April 08, 2018
 */
public interface AddNote {
    //Создать запись
    void create (Note note);

    //Редактировать зпись
    void update (Note note);

    //Удалить запись
    void delete (Note note);
}
