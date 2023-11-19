package com.example.demo1.logic.commands;

import com.example.demo1.exceptions.CommandException;
import com.example.demo1.logic.ICommand;
import jakarta.servlet.http.HttpServletRequest;

public class ErrorPage implements ICommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}
