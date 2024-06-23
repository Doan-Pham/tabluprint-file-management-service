package com.tabluprint.file_management_service.controller;

import java.util.List;

public class SpreadsheetData {
    private List<List<String>> data;

    public List<List<String>> getData() {
        return data;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
