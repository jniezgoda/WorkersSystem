package jn.manage.worker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
public class Control {

    HashMap<Integer, Employee> employees = new HashMap<Integer, Employee>(){{
        for (int i = 1; i <= 15; i++) {
            put(i, new Employee(
                    i+"TestImie",
                    i+"TestNazwisko",
                    i+"TESTER",
                    i*10000)
            );
        }
    }};
    EmployeeManager employeeManager = new EmployeeManager(employees);
    @GetMapping("/")
    public String index( Model model) {
        model.addAttribute("employees", employeeManager.listAllEmployees());
        return "index";
    }
    @PostMapping("edited")
    public String edited(@RequestParam(name = "id") String employeeID,
                         @RequestParam(name = "firstName") String firstName,
                         @RequestParam(name = "lastName") String lastName,
                         @RequestParam(name = "position") String position,
                         @RequestParam(name = "salary") String salary, Model model) {
        Employee employee = new Employee(firstName, lastName, position, Integer.parseInt(salary));
        employee.employeeID = Integer.parseInt(employeeID);
        employeeManager.updateEmployee(Integer.parseInt(employeeID), employee);

        model.addAttribute("employees", employeeManager.listAllEmployees());
        return "index";
    }

    @PostMapping("add")
    public String add(@RequestParam(name = "firstName") String firstName,
                      @RequestParam(name = "lastName") String lastName,
                      @RequestParam(name = "position") String position,
                      @RequestParam(name = "salary") String salary, Model model) {


        employeeManager.addEmployee(firstName, lastName, position, Integer.parseInt(salary));

        model.addAttribute("employees", employeeManager.listAllEmployees());
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam(name = "searchValue") String lastName, Model model) {
        if (!employeeManager.searchEmployeesByLastName(lastName).isEmpty())
            model.addAttribute("employees", employeeManager.searchEmployeesByLastName(lastName));
        return "index";
    }
    @GetMapping("/edit")
    public String editEmployee(@RequestParam(name = "id") String employeeID, Model model) {
        model.addAttribute("employee", employeeManager.searchEmployeeByID(Integer.parseInt(employeeID)));
        return "edit";
    }

    @GetMapping("/lay-off")
    public String layOffEmployee(@RequestParam(name = "id") String employeeID, Model model) {
        employees.remove(Integer.parseInt(employeeID));
        model.addAttribute("employees", employeeManager.listAllEmployees());
        return "index";
    }

    @GetMapping("/add-worker")
    public String addWorker() {
        return "add-worker";

    }
}
