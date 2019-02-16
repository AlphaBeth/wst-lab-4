package ru.ifmo.wst.lab1.client;

import lombok.Getter;
import lombok.SneakyThrows;
import ru.ifmo.wst.lab.Box;
import ru.ifmo.wst.lab1.ExterminatusResourceClient;
import ru.ifmo.wst.lab1.command.Command;
import ru.ifmo.wst.lab1.command.CommandArg;
import ru.ifmo.wst.lab1.command.CommandArgDescription;
import ru.ifmo.wst.lab1.command.CommandInterpreter;
import ru.ifmo.wst.lab1.command.NoLineFoundException;
import ru.ifmo.wst.lab1.command.args.DateArg;
import ru.ifmo.wst.lab1.command.args.EmptyStringToNull;
import ru.ifmo.wst.lab1.command.args.LongArg;
import ru.ifmo.wst.lab1.command.args.StringArg;
import ru.ifmo.wst.lab1.model.ExterminatusEntity;
import ru.ifmo.wst.lab1.model.Filter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class ExterminatusResourceConsoleClient {
    private final CommandInterpreter commandInterpreter;
    @Getter
    private boolean exit = false;
    private ExterminatusResourceClient service;

    public ExterminatusResourceConsoleClient(String baseUrl) {
        this.service = new ExterminatusResourceClient(baseUrl);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Command<Void> infoCommand = new Command<>("info", "Print help for commands", (arg) -> this.info());
        Command<Box<String>> changeEndpointAddressCommand = new Command<>("endpoint", "Changes endpoint address",
                Arrays.asList(
                        new CommandArg<>(new StringArg("url", "New exterminatus endpoint url"), Box::setValue)
                ), Box::new, this::changeEndpointUrl
        );
        Command<Void> findAllCommand = new Command<>("findAll", "Return list of all exterminatus entities", (arg) -> this.findAll());
        Command<Filter> filterCommand = new Command<>("filter",
                "Filter exterminatus entities by column values (ignore case contains for strings), empty values are ignored",
                asList(
                        new CommandArg<>(toNull(new LongArg("id", "Exterminatus id")), Filter::setId),
                        new CommandArg<>(toNull(new StringArg("initiator", "Initiator name")), Filter::setInitiator),
                        new CommandArg<>(toNull(new StringArg("reason", "Reason of exterminatus")), Filter::setReason),
                        new CommandArg<>(toNull(new StringArg("method", "Method of exterminatus")), Filter::setMethod),
                        new CommandArg<>(toNull(new StringArg("planet", "Exterminated planet")), Filter::setPlanet),
                        new CommandArg<>(toNull(new DateArg("date", "Date of exterminatus")), Filter::setDate)
                ),
                Filter::new, this::filter);
        Command<Void> exitCommand = new Command<>("exit", "Exit application", (arg) -> this.exit = true);
        this.commandInterpreter = new CommandInterpreter(() -> readLine(bufferedReader),
                System.out::print, asList(infoCommand, changeEndpointAddressCommand, findAllCommand, filterCommand, exitCommand),
                "No command found",
                "Enter command", "> ");

    }

    public void start() {
        while (!exit) {
            try {
                commandInterpreter.readCommand();
            } catch (NoLineFoundException exc) {
                exit = true;
            } catch (Exception exc) {
                System.out.println("Unknown error");
                exc.printStackTrace();
            }

        }
    }

    public void changeEndpointUrl(Box<String> box) {
        changeEndpointUrl(box.getValue());
    }

    public void changeEndpointUrl(String endpointUrl) {
        service = new ExterminatusResourceClient(endpointUrl);
    }

    public void findAll() {
        List<ExterminatusEntity> all = service.findAll();
        System.out.println("Result of operation:");
        all.forEach(System.out::println);
    }

    public void filter(Filter filterArg) {
        List<ExterminatusEntity> filterRes = service.filter(filterArg);
        System.out.println("Result of operation:");
        filterRes.forEach(System.out::println);
    }

    public void info() {
        commandInterpreter.info();
    }


    @SneakyThrows
    private static String readLine(BufferedReader reader) {
        return reader.readLine();
    }

    private static <T> CommandArgDescription<T> toNull(CommandArgDescription<T> commandArg) {
        return new EmptyStringToNull<>(commandArg);
    }

}
