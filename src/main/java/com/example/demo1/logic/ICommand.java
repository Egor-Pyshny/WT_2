package com.example.demo1.logic;

import com.example.demo1.exceptions.CommandException;
import com.example.demo1.exceptions.DAOException;
import jakarta.servlet.http.HttpServletRequest;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface ICommand {
    String execute(HttpServletRequest request) throws CommandException, ParserConfigurationException, IOException, DAOException;
}
