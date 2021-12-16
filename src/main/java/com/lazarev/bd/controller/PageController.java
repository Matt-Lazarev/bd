package com.lazarev.bd.controller;

import com.lazarev.bd.repository.*;
import com.lazarev.bd.vo.ObjectData;
import com.lazarev.bd.vo.TableValueObject;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class PageController {

    private static final List<String> tables =
            List.of("brigade", "client", "object", "contract", "manager",
                    "material", "object_address","project", "used_material", "worker");

    private final MainRepository repository;
    private final ObjectRepository objectRepository;
    private final BrigadeRepository brigadeRepository;
    private final ClientRepository clientRepository;
    private final ContractRepository contractRepository;
    private final ManagerRepository managerRepository;
    private final MaterialRepository materialRepository;
    private final ObjectAddressRepository objectAddressRepository;
    private final ProjectRepository projectRepository;
    private final UsedMaterialRepository usedMaterialRepository;
    private final WorkerRepository workerRepository;

    @GetMapping("/home")
    public String getMainPage(Model model){
        model.addAttribute("tvo", new TableValueObject());
        model.addAttribute("objectData", new ObjectData());
        model.addAttribute("tables", tables);
        return "main";
    }

    @PostMapping("/select")
    public String getAllObjects(@ModelAttribute("tvo") TableValueObject tvo, Model model){
        List<Map<String, Object>> result = repository.getAllObjects(tvo.getTable());
        List<String> headers = getHeaders(tvo.getTable());
        model.addAttribute("headers", headers);
        model.addAttribute("objects", result);
        return "objects";
    }

    @PostMapping("/select/id")
    public String getObjectById(@ModelAttribute("tvo") TableValueObject tvo, Model model){
        List<Map<String, Object>> result = repository.getObjectById(tvo.getTable(), tvo.getId());
        List<String> headers = getHeaders(tvo.getTable());
        model.addAttribute("headers", headers);
        model.addAttribute("objects", result);
        return "objects";
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateField(@ModelAttribute("objectData") ObjectData objectData, Model model){
        String table = objectData.getField1();
        Integer id = Integer.parseInt(objectData.getField2());
        String field = objectData.getField3();
        String newValue =  objectData.getField4();

        switch (table) {
            case "object":
                switch (field) {
                    case "name":
                        objectRepository.updateObjectNameById(id, newValue);
                        break;
                    case "description":
                        objectRepository.updateObjectDescriptionById(id, newValue);
                }
                break;
            case "brigade":
                if ("name".equals(field)) {
                    brigadeRepository.updateBrigadeNameById(id, newValue);
                }
                break;
            case "client":
                switch (field) {
                    case "firstname":
                        clientRepository.updateClientFirstnameById(id, newValue);
                        break;
                    case "lastname":
                        clientRepository.updateClientLastnameById(id, newValue);
                        break;
                    case "payment_account":
                        clientRepository.updateClientPaymentAccountById(id, newValue);
                }
                break;
            case "contract":
                switch (field) {
                    case "name":
                        contractRepository.updateContractNameById(id, newValue);
                        break;
                    case "conclusion_at":
                        contractRepository.updateContractConclusionAtById(id, getDate(newValue));
                }
                break;
            case "manager":
                switch (field) {
                    case "firstname":
                        managerRepository.updateManagerFirstnameById(id, newValue);
                        break;
                    case "lastname":
                        managerRepository.updateManagerLastnameById(id, newValue);
                        break;
                    case "phone_number":
                        managerRepository.updateManagePhoneNumberById(id, newValue);
                }
                break;
            case "material":
                switch (field) {
                    case "name":
                        materialRepository.updateMaterialNameById(id, newValue);
                        break;
                    case "unit":
                        materialRepository.updateMaterialUnitById(id, newValue);
                        break;
                    case "price":
                        int price = Integer.parseInt(newValue);
                        materialRepository.updateMaterialPriceById(id, price);
                }
                break;
            case "object_address":
                switch (field) {
                    case "description":
                        objectAddressRepository.updateObjectAddressCityById(id, newValue);
                        break;
                    case "work_start_at":
                        objectAddressRepository.updateObjectAddressStreetById(id, newValue);
                        break;
                    case "work_end_at":
                        int house = Integer.parseInt(newValue);
                        objectAddressRepository.updateObjectAddressHouseById(id, house);
                }
                break;
            case "project":
                switch (field) {
                    case "description":
                        projectRepository.updateProjectDescriptionById(id, newValue);
                        break;
                    case "work_start_at":
                        projectRepository.updateProjectWorkStartAtById(id, getDate(newValue));
                        break;
                    case "work_end_at":
                        projectRepository.updateProjectWorkEndAtById(id, getDate(newValue));
                }
                break;
            case "used_material":
                switch (field) {
                    case "amount":
                        int amount = Integer.parseInt(newValue);
                        usedMaterialRepository.updateUsedMaterialAmountById(id,amount);
                        break;
                    case "date":
                        usedMaterialRepository.updateUsedMaterialDateById(id, getDate(newValue));
                }
                break;
            case "worker":
                switch (field) {
                    case "firstname":
                        workerRepository.updateWorkerFirstnameById(id, newValue);
                        break;
                    case "lastname":
                        workerRepository.updateWorkerLastnameById(id, newValue);
                }
        }

        List<Map<String, Object>> result = repository.getAllObjects(table);
        List<String> headers = getHeaders(table);
        model.addAttribute("headers", headers);
        model.addAttribute("objects", result);
        return "objects";
    }

    @PostMapping("/insert")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String insert(@ModelAttribute("objectData") ObjectData objectData, Model model){
        String table = objectData.getField1();
        model.addAttribute("objectData", new ObjectData());
        model.addAttribute("table", table);
        List<String> headers = getHeadersWithoutPrimaryKey(table);
        model.addAttribute("headers", headers);
        return "input";
    }

    @PostMapping("/input/{table}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String insertInput(@ModelAttribute("objectData") ObjectData objectData, Model model, @PathVariable String table){
        String f1 = objectData.getField1() == null ? "" : objectData.getField1();
        String f2 = objectData.getField2() == null ? "" : objectData.getField2();
        String f3 = objectData.getField3() == null ? "" : objectData.getField3();
        String f4 = objectData.getField4() == null ? "" : objectData.getField4();

        String[] params = null;
        switch (table) {
            case "brigade":
                params = new String[]{f1, f2};
                break;
            case "client":
            case "manager":
            case "object":
            case "worker":
            case "material":
                params = new String[]{f1, f2, f3};
                break;
            case "contract":
            case "project":
            case "object_address":
            case "used_material":
                params = new String[]{f1, f2, f3, f4};
                break;
        }

        repository.insert(table, params);

        List<String> headers = getHeaders(table);
        model.addAttribute("headers", headers);
        List<Map<String, Object>> result = repository.getAllObjects(table);
        model.addAttribute("objects", result);
        return "objects";
    }

    @PostMapping("/delete/id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteById(@ModelAttribute("tvo") TableValueObject tvo, Model model){
        repository.deleteById(tvo.getTable(), tvo.getId());
        List<Map<String, Object>> result = repository.getAllObjects(tvo.getTable());
        List<String> headers = getHeaders(tvo.getTable());
        model.addAttribute("headers", headers);
        model.addAttribute("objects", result);
        return "objects";
    }

    @PostMapping("/order")
    public String getObjectsOrderBy(@ModelAttribute("objectData") ObjectData objectData, Model model){
        String table = objectData.getField1();
        String field = objectData.getField2();
        String order = objectData.getField3();

        List<Map<String, Object>> result = repository.getAllObjectsOrderBy(table, field, order);
        List<String> headers = getHeaders(table);
        model.addAttribute("headers", headers);
        model.addAttribute("objects", result);
        return "objects";
    }

    @PostMapping("/like")
    public String getAllObjectsLike(@ModelAttribute("objectData") ObjectData objectData, Model model){
        String table = objectData.getField1();
        String field = objectData.getField2();
        String like =  objectData.getField3();

        List<Map<String, Object>> result = repository.getAllObjectsLike(table, field, like);
        List<String> headers = getHeaders(table);
        model.addAttribute("headers", headers);
        model.addAttribute("objects", result);
        return "objects";
    }

    @PostMapping("/between")
    public String getAllObjectsBetween(@ModelAttribute("objectData") ObjectData objectData, Model model){
        String table = objectData.getField1();
        String field = objectData.getField2();
        String from = objectData.getField3();
        String to = objectData.getField4();

        if(!checkInput(from)){
           from = "'" + from + "'";
           to = "'" + to + "'";
        }

        List<Map<String, Object>> result = repository.getAllObjectsBetween(table, field, from, to);
        List<String> headers = getHeaders(table);
        model.addAttribute("headers", headers);
        model.addAttribute("objects", result);
        return "objects";
    }
    
    private List<String> getHeaders(String table){
        List<Map<String, Object>> result = repository.getAllObjects(table);
        return result.stream()
                .flatMap(x -> x.entrySet().stream())
                .map(Map.Entry::getKey)
                .distinct()
                .collect(Collectors.toList());
    }

    @PostMapping("/filter")
    public String getFilteredObjects(@ModelAttribute("objectData") ObjectData objectData, Model model){
        String table = objectData.getField1();
        String field1 = objectData.getField2();
        String field2 = objectData.getField4();
        String field3 = objectData.getField6();
        String value1 =  objectData.getField3();
        String value2 =  objectData.getField5();
        String value3 =  objectData.getField7();

        String comp1 = objectData.getCompareSign1();
        String comp2 = objectData.getCompareSign2();
        String comp3 = objectData.getCompareSign3();

        String ls1 = objectData.getLogical12();
        String ls2 = objectData.getLogical23();

        List<Map<String, Object>> result = null;
        if(field2.isEmpty() && field3.isEmpty()){
            result = repository.getFilteredObjectsOneFilter(table, field1, comp1, value1);
        }
        else if (field3.isEmpty()){
            result = repository.getFilteredObjectsTwoFilters(table, field1, comp1, value1, field2, comp2, value2, ls1);
        }
        else {
            result = repository.getFilteredObjectsThreeFilters(
                    table, field1, comp1, value1, field2, comp2, value2, field3, comp3, value3, ls1, ls2);
        }

        List<String> headers = getHeaders(table);
        model.addAttribute("headers", headers);
        model.addAttribute("objects", result);
        return "objects";
    }

    private List<String> getHeadersWithoutPrimaryKey(String table){
        List<Map<String, Object>> result = repository.getAllObjects(table);
        return result.stream()
                .flatMap(x -> x.entrySet().stream())
                .map(Map.Entry::getKey)
                .distinct()
                .filter(x -> !x.equals(table + "_id"))
                .collect(Collectors.toList());
    }

    private LocalDate getDate(Object value){
        int[] params = Arrays.stream(((String) value).split("-"))
                .mapToInt(Integer::parseInt)
                .toArray();
        return LocalDate.of(params[0], params[1], params[2]);
    }

    private boolean checkInput(String from) {
        return from.matches("\\d+");
    }

    private List<String> addPrefix(List<String> list, String prefix){
        return list.stream()
                .map(x -> prefix + x)
                .collect(Collectors.toList());
    }

}
