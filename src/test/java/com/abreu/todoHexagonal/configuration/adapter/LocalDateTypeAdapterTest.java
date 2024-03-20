package com.abreu.todoHexagonal.configuration.adapter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LocalDateTypeAdapterTest {

    private final LocalDateTypeAdapter adapter = new LocalDateTypeAdapter();
    private final Gson gson = new Gson();

    @Test
    void shouldDeserialize() {
        JsonElement jsonElement = new JsonPrimitive("2022-01-01");
        LocalDate result = adapter.deserialize(jsonElement, LocalDate.class, null);
        assertEquals(LocalDate.of(2022, 1, 1), result);
    }

    @Test
    void shouldSerialize() {
        LocalDate date = LocalDate.of(2022, 1, 1);
        JsonElement result = adapter.serialize(date, LocalDate.class, null);
        assertEquals(new JsonPrimitive("2022-01-01"), result);
    }

    @Test
    void shouldWrite() throws Exception {
        LocalDate date = LocalDate.of(2022, 1, 1);
        StringWriter stringWriter = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(stringWriter);
        adapter.write(jsonWriter, date);
        jsonWriter.close();
        assertEquals("\"2022-01-01\"", stringWriter.toString());
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
        String input = "\"2022-01-01\"";
        JsonReader jsonReader = new JsonReader(new StringReader(input));
        LocalDate result = adapter.read(jsonReader);
        assertEquals(LocalDate.of(2022, 1, 1), result);
    }

    @Test
    void shouldReadNull() throws Exception {
        String input = "null";
        JsonReader jsonReader = new JsonReader(new StringReader(input));
        LocalDate result = adapter.read(jsonReader);
        assertNull(result);
    }
}
