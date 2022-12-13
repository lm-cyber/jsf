package com.example.jsf;

import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Named
@Singleton
public class PageNavigation {
    public String goToIndex() {
        return "go";
    }

    public String goToMain() {
        return "go";
    }
}
