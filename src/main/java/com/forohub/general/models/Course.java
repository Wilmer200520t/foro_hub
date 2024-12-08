package com.forohub.general.models;

public enum Course {

    DATABASE_SYSTEMS("BE101", "Database Systems"),
    OBJECT_ORIENTED_PROGRAMMING("BE102", "Object-Oriented Programming"),
    WEB_DEVELOPMENT("BE103", "Web Development"),
    API_DESIGN("BE104", "API Design and Development"),
    MICROSERVICES_ARCHITECTURE("BE105", "Microservices Architecture"),
    CLOUD_COMPUTING("BE106", "Cloud Computing"),
    ADVANCED_JAVA("BE107", "Advanced Java Programming"),
    SPRING_FRAMEWORK("BE108", "Spring Framework"),
    NODEJS_DEVELOPMENT("BE109", "Node.js Development"),
    DATABASE_ADMINISTRATION("BE110", "Database Administration"),

    INTRODUCTION_TO_DEVOPS("DO101", "Introduction to DevOps"),
    CONTINUOUS_INTEGRATION("DO102", "Continuous Integration"),
    CONTINUOUS_DEPLOYMENT("DO103", "Continuous Deployment"),
    INFRASTRUCTURE_AS_CODE("DO104", "Infrastructure as Code"),
    CONTAINERIZATION_DOCKER("DO105", "Containerization with Docker"),
    ORCHESTRATION_KUBERNETES("DO106", "Orchestration with Kubernetes"),
    MONITORING_LOGGING("DO107", "Monitoring and Logging"),
    SECURITY_IN_DEVOPS("DO108", "Security in DevOps"),
    AUTOMATION_WITH_ANSIBLE("DO109", "Automation with Ansible"),
    CLOUD_SERVICES_AWS("DO110", "Cloud Services with AWS"),
    CLOUD_SERVICES_AZURE("DO111", "Cloud Services with Azure"),
    CONFIGURATION_MANAGEMENT("DO112", "Configuration Management"),


    SOFTWARE_ENGINEERING_PRACTICES("SE101", "Software Engineering Practices"),
    VERSION_CONTROL_GIT("SE102", "Version Control with Git"),
    SOFTWARE_TESTING("SE103", "Software Testing and Quality Assurance"),
    AGILE_METHODOLOGIES("SE104", "Agile Methodologies"),
    SYSTEM_DESIGN("SE105", "System Design and Architecture");

    private final String code;
    private final String name;

    Course(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}

