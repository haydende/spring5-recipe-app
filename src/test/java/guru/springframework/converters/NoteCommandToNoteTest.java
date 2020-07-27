package guru.springframework.converters;

import guru.springframework.commands.NoteCommand;
import guru.springframework.domain.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoteCommandToNoteTest {

    public static final String DESCRIPTION = "description";
    public static final Long ID = Long.valueOf(1L);

    NoteCommandToNote converter;

    @BeforeEach
    void setUp() throws Exception {
        converter = new NoteCommandToNote();
    }

    @Test
    void nullTest() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void emptyObjectTest() throws Exception {
        assertNotNull(converter.convert(new NoteCommand()));
    }

    @Test
    void convert() {
        NoteCommand note = new NoteCommand();
        note.setId(ID);
        note.setDescription(DESCRIPTION);

        // when
        Note noteC = converter.convert(note);

        // then
        assertNotNull(noteC);
        assertEquals(ID, noteC.getId());
        assertEquals(DESCRIPTION, noteC.getDescription());
    }

}