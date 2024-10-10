package ru.sberstart.finalproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import ru.sberstart.finalproject.domain.enitity.interfaces.Stateful;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootTest
public class StorageScanner {
    @Value("${test-resource.package-name.path}")
    private String PATH_TO_DIRECTORY;

    @Autowired
    private ObjectMapper objectMapper;

    public <T> Optional<T> findFirst(Class<T> clazz) {
        try (Stream<T> stream = streamOfConvertedFile(clazz)) {
            return stream.findFirst();
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла для класса " + clazz.getName() + ": " + e.getMessage());
            return Optional.empty();
        }
    }

    public <T extends Stateful> Optional<T> findFirstWithState(Class<T> clazz, String state) {
        try (Stream<T> stream = streamOfConvertedFile(clazz)) {
            return stream
                    .filter(record -> record.getState().toString().equals(state))
                    .findFirst();
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла для класса " + clazz.getName() + ": " + e.getMessage());
            return Optional.empty();
        }
    }

    public <T> Stream<T> findAll(Class<T> clazz) {
        try {
            return streamOfConvertedFile(clazz);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла для класса " + clazz.getName() + ": " + e.getMessage());
            return Stream.empty();
        }
    }

    private <T> Stream<T> streamOfConvertedFile(Class<T> clazz) throws IOException {
        Path path = Path.of(buildPath(clazz));
        if (!Files.exists(path)) {
            System.out.println("Внимание! Файл с записями " + clazz.getName() + " не найден.");
            return Stream.empty();
        }
        return Files.lines(path)
                .map(json -> convertJsonTo(clazz, json))
                .filter(Objects::nonNull);
    }

    private String buildPath(Class<?> clazz) {
        return PATH_TO_DIRECTORY + clazz.getName() + "s.txt";
    }

    private <T> T convertJsonTo(Class<T> clazz, String json) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            return null;
        }
    }
}
