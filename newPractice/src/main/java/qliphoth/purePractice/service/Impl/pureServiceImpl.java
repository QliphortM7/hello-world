package qliphoth.purePractice.service.Impl;

import qliphoth.purePractice.service.pureService;

public class pureServiceImpl implements pureService {

    public void hello(String name) {
        if (name == null || "".equals(name.trim())) {
            throw new RuntimeException ("parameter is null");
        }
        System.out.println(name);
    }
}
