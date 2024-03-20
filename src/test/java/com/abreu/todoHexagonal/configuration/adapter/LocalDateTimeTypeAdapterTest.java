package com.abreu.todoHexagonal.configuration.adapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LocalDateTimeTypeAdapterTest {

    private final LocalDateTimeTypeAdapter adapter = new LocalDateTimeTypeAdapter();

    @Test
    void shouldDeserialize() {
        JsonElement jsonElement = new JsonPrimitive("2022-01-01T12:00:00");
        LocalDateTime result = adapter.deserialize(jsonElement, LocalDateTime.class, null);
        assertEquals(LocalDateTime.of(2022, 1, 1, 12, 0), result);
    }

    @Test
    void shouldSerialize() {
        LocalDateTime localDateTime = LocalDateTime.of(2022, 1, 1, 12, 0);
        JsonElement result = adapter.serialize(localDateTime, LocalDateTime.class, null);
        assertEquals(new JsonPrimitive("2022-01-01T12:00:00"), result);
    }

    @Test
    void shouldWrite() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2022, 1, 1, 12, 0);
        StringWriter stringWriter = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(stringWriter);
        adapter.write(jsonWriter, localDateTime);
        jsonWriter.close();
        assertEquals("\"2022-01-01T12:00:00\"", stringWriter.toString());
    }

    @Test
    void shouldWriteNull() throws Exception {
        StringWriter stringWriter = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(stringWriter);
        adapter.write(jsonWriter, null);
        jsonWriter.close();
        assertEquals("null", stringWriter.toString());
    }

    @Test
    void shouldRead() throws Exception {
        String input = "\"2022-01-01T12:00:00\"";
        JsonReader jsonReader = new JsonReader(new StringReader(input));
        LocalDateTime result = adapter.read(jsonReader);
        assertEquals(LocalDateTime.of(2022, 1, 1, 12, 0), result);
    }

    @Test
    void shouldReadNull() throws Exception {
        JsonReader jsonReader = new JsonReader(new StringReader("null"));
        LocalDateTime result = adapter.read(jsonReader);
        assertNull(result);
    }
}
