package com.mkyong.controller;

import com.mkyong.service.AccessMService;
import com.mkyong.service.AccessService;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ClassResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;


@SpringUI(path = "")
public class ViewHome extends UI {
    @Autowired
    private AccessService accessService;
    @Autowired
    AccessMService accessMService;
    private VerticalLayout mainLayout;
    private TextField textField;
    private Button searchButton;
    private Label label;
    private Label label2;
    private HorizontalLayout mainDescription;
    private Label lastIndexedLabel;
    private Label country;
    private Label employees;
    private Label aRank;
    private Label qRank;
    private Label MJRank;
    private Label MJTLDRank;
    private Label refSN;
    private Label refIP;
    private Label followers;
    private Label CDimensions;


    @Override
    protected void init(VaadinRequest vaadinRequest) {
        mainLayout();
        setHeader();
        setForm();
        dashboardTitle();

        searchButton.addClickListener(clickEvent ->
        {
            if (!textField.getValue().equals("")) {
                try {
                    updateUi();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else
                Notification.show("لطفا یک سایت را وارد کنید");
        });
    }

    private void mainLayout() {
        mainLayout = new VerticalLayout();
        mainLayout.setWidth("100%");
        mainLayout.setSpacing(true);
        mainLayout.setMargin(true);
        mainLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(mainLayout);
    }

    private void setHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        Label title = new Label("مشخصات سایت");

        header.addComponent(title);
        mainLayout.addComponent(header);
    }

//    private void setLogo() {
//        HorizontalLayout logo = new HorizontalLayout();
//        logo.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
//        Image img = new Image(null, new ClassResource("/static/img.png"));
//        logo.setWidth("500px");
//        logo.setHeight("500px");
//        logo.addComponent(img);
//        mainLayout.addComponent(logo);
//    }


    private void setForm() {
        HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        formLayout.setSpacing(true);
        formLayout.setMargin(true);

        textField = new TextField();
        textField.setWidth("1000px");
        formLayout.addComponent(textField);


        searchButton = new Button();
        searchButton.setIcon(VaadinIcons.SEARCH);
        formLayout.addComponent(searchButton);


        mainLayout.addComponent(formLayout);
    }

    private void dashboardTitle() {
        HorizontalLayout dashboard = new HorizontalLayout();
        dashboard.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        label = new Label("");
        label.addStyleName(ValoTheme.LABEL_H2);
        label.addStyleName(ValoTheme.LABEL_LIGHT);

        label2 = new Label("");
        label2.setStyleName(ValoTheme.LABEL_BOLD);
        label2.setStyleName(ValoTheme.LABEL_H1);

        dashboard.addComponents(label, label2);
        mainLayout.addComponent(dashboard);

    }

    private void dashboardDetails() {
        mainDescription = new HorizontalLayout();
        mainDescription.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        VerticalLayout desriptionLayout = new VerticalLayout();
        desriptionLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        lastIndexedLabel = new Label("lastIndexed");
        lastIndexedLabel.setStyleName(ValoTheme.LABEL_SUCCESS);
        desriptionLayout.addComponent(lastIndexedLabel);


        country = new Label("country");
        desriptionLayout.addComponent(country);

        qRank = new Label("qRank");
        desriptionLayout.addComponent(qRank);
        CDimensions = new Label("CDimensions");
        desriptionLayout.addComponent(CDimensions);
        refSN = new Label("refSN");
        desriptionLayout.addComponent(refSN);
        MJTLDRank = new Label("MJTLDRank");
        desriptionLayout.addComponent(MJTLDRank);
        refIP = new Label("refIP");
        desriptionLayout.addComponent(refIP);
        followers = new Label("followers");
        desriptionLayout.addComponent(followers);
        MJRank = new Label("MJRank");
        desriptionLayout.addComponent(MJRank);


        VerticalLayout pressureLayout = new VerticalLayout();
        pressureLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        aRank = new Label("aRank");
        desriptionLayout.addComponent(aRank);
        employees = new Label("employees");
        pressureLayout.addComponent(employees);
        mainDescription.addComponents(desriptionLayout, pressureLayout);
        mainLayout.addComponents(desriptionLayout, pressureLayout);
    }

    private void updateUi() throws JSONException {
        String site = textField.getValue();
        accessService.setSiteName(site);
        JSONObject metaObj;
        String country = null, lookup = null;
        int aRank = 0, qRank = 0, MJRank = 0, MJTLDRank = 0, refSN = 0, refIP = 0, followers = 0, lastIndexed = 0, CDimensions = 0, employees = 0;
        JSONArray jsonArray = accessService.returnResults();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject resultObject = jsonArray.getJSONObject(i);
            lastIndexed = resultObject.getInt("LastIndexed");
            lookup = resultObject.getString("Lookup");
            metaObj = resultObject.getJSONObject("Meta");
            JSONObject attributesObj = resultObject.getJSONObject("Attributes");
            country = metaObj.getString("Country");
            aRank = metaObj.getInt("ARank");
            qRank = metaObj.getInt("QRank");
            MJRank = attributesObj.getInt("MJRank");
            MJTLDRank = attributesObj.getInt("MJTLDRank");
            refSN = attributesObj.getInt("RefSN");
            refIP = attributesObj.getInt("RefIP");
            followers = attributesObj.getInt("Followers");
            CDimensions = attributesObj.getInt("CDimensions");
            employees = attributesObj.getInt("Employees");

        }
       // setLogo();
        dashboardDetails();

        label.setValue("lookup:");
        label2.setValue(lookup);
        lastIndexedLabel.setValue("lastIndexed:" + lastIndexed);
        this.country.setValue("country: " + country);
        this.employees.setValue("employees: " + employees + "");
        this.aRank.setValue("ARank: " + aRank);
        this.qRank.setValue("QRank: " + qRank);
        this.MJRank.setValue("MJRank: " + MJRank);
        this.MJTLDRank.setValue("MJTLDRank: " + MJTLDRank);
        this.refSN.setValue("refSN: " + refSN);
        this.refIP.setValue("refIP: " + refIP);
        this.CDimensions.setValue("CDimensions: " + CDimensions);
        this.followers.setValue("followers: " + followers);
        accessMService.save(lookup, country, employees, aRank, qRank, MJRank, MJTLDRank, refSN, refIP, followers, lastIndexed, CDimensions);


    }
}


