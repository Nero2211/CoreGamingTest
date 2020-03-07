package com.cg.Server;

import com.cg.Server.Service.JsonUtil;
import com.cg.Server.Service.SpinService;
import com.cg.Server.Service.TableService;
import spark.Request;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        init();
    }

    private static void init() {
        port(8008);
        post("/serve", (req, res) -> initServe(req));

        get("/table", (req, res) -> TableService.initBasicWeightTable(), JsonUtil.json());

        get("/spin", (req, res) -> SpinService.initSpinService(), JsonUtil.json());
    }

    private static String initServe(Request req){
        switch(req.body()) {
            case "Hello":
                return "Hello stranger!";
            //other scenarios could go here ;)
            default:
                return "Error! No or invalid request name specified! (" + req.body() + ")";
        }
    }
}
