package org.tai.todolist.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Karigen B
 * @create 2022-08-22 23:52
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/v1")
    public String v1(@RequestBody Map<String, Object> msgs) {
        Object userid = msgs.get("userid");
        System.out.println("userid = " + userid);

        // List<?>
        Object taskId = msgs.get("taskId[]");
        System.out.println("taskId[] = " + taskId);
        return "success";
    }

    @GetMapping("/v2")
    public String v2(@RequestParam("msg") Integer[] msgs) {
        System.out.println(msgs.length);
        System.out.println(Arrays.toString(msgs));
        return "success";
    }

    @PostMapping("/v3")
    public String v3(@RequestBody String msgs) throws JsonProcessingException {
        System.out.println(msgs);
        JsonNode msg = new ObjectMapper().readTree(msgs).get("msg");
        if (msg.isArray()) {
            for (JsonNode node : msg) {
                System.out.println(node.asInt());
            }
        }
        return "success";
    }

    // 请求能发出来但是读取不到
    @PostMapping("/v4")
    public String v4(@RequestBody Msg msg) {
        System.out.println(msg);
        return "success";
    }

}

record Msg(Integer userid, Integer[] taskId){}