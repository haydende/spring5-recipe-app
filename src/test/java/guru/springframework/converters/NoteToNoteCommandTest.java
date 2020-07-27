package guru.springframework.converters;

import guru.springframework.commands.NoteCommand;
import guru.springframework.domain.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoteToNoteCommandTest {

    public static final String DESCRIPTION = "description";
    public static final Long ID = Long.valueOf(1L);

    NoteToNoteCommand converter;

    @BeforeEach
    void setUp() throws Exception {
        converter = new NoteToNoteCommand();
    }

    @Test
    void nullTest() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void emptyObjectTest() throws Exception {
        assertNotNull(converter.convert(new Note()));
    }

    @Test
    void convert() {
        Note note = new Note();
        note.setId(ID);
        note.setDescription(DESCRIPTION);

        // when
        NoteCommand noteC = converter.convert(note);

        // then
        assertNotNull(noteC);
        assertEquals(ID, noteC.getId());
        assertEquals(DESCRIPTION, noteC.getDescription());
    }

}