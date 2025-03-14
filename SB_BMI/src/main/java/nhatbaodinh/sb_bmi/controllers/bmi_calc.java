package nhatbaodinh.sb_bmi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class bmi_calc {
  @RequestMapping("/bmi")
  public String bmi(@RequestParam(name = "height", required = false, defaultValue = "0") float height,
                    @RequestParam(name = "weight", required = false, defaultValue = "0") float weight,
                    ModelMap model) {

    if (height <= 0 || weight <= 0) {
      model.addAttribute("error", "Nhập chiều cao và cân nặng hợp lệ!");
      return "bmi";
    }

    float bmi = weight / (height * height);
    String result;

    if (bmi < 18.5) {
      result = "Ốm!";
    } else if (bmi < 25) {
      result = "Bình thường!";
    } else if (bmi < 30) {
      result = "Thừa cân!";
    } else {
      result = "Béo phì!";
    }

    model.addAttribute("height", height);
    model.addAttribute("weight", weight);
    model.addAttribute("result", result);
    return "bmi";
  }
}
