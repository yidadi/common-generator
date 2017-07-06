package com.even.common.generator.enums;

import com.even.common.generator.util.GeneratorConfiguration;

import java.io.File;

/**
 * 模板文件枚举(具体要生成的模板文件的后缀名称)
 *
 * @author yidadi
 * @date 2016-09-04 22：29
 */
public enum TemplateFile {

    /**
     * Mapper接口
     */
    DAO_INTERFACE("Mapper.java", "mapper"),

    /**
     * Mapper实现
     */
    DAO_IMPLEMENT("Mapper.xml", "mapper"),

    /**
     * Service接口
     */
    SERVICE_INTERFACE("Service.java", "service"),

    /**
     * Service实现
     */
    SERVICE_IMPLEMENT("ServiceImpl.java", "service/impl"),

    /**
     * Controller
     */
    CONTROLLER("Controller.java", "controller"),

    /**
     * Entity
     */
    ENTITY("Entity.java", "entity"),

    /**
     * Dto
     */
    DTO("Dto.java", "dto"),

    /**
     * Query
     */
    QUERY("Query.java", "query"),

    /**
     * Page
     */
    PAGE("Page.java", "page"),

    /**
     * DaoTest
     */
    DAO_TEST("MapperTest.java", "mapper"),

    /**
     * ServiceTest
     */
    SERVICE_TEST("ServiceTest.java", "service");

    private static final String DOMAIN = "-domain";
    private static final String MICRO_SERVICE = "-micro-service";

    public static String getFileName(String entityName, TemplateFile file) {
        String basePath = getBasePath();
        String projectPackage = null;
        String packageName = null;
        if (TemplateFile.QUERY == file || TemplateFile.DTO == file || TemplateFile.PAGE == file) {
            projectPackage = GeneratorConfiguration.projectName + DOMAIN + File.separator;
            packageName = BasePackagePath.BASE.getPath() + GeneratorConfiguration.projectName + "/domain/";
        } else {
            projectPackage = GeneratorConfiguration.projectName + MICRO_SERVICE + File.separator;
            packageName = BasePackagePath.BASE.getPath() + GeneratorConfiguration.projectName + "/microservice/";
        }
        String mainPath = null;
        if (TemplateFile.DAO_TEST == file || TemplateFile.SERVICE_TEST == file) {
            mainPath = BasePackagePath.TEST.getPath();
        } else {
            mainPath = BasePackagePath.MAIN.getPath();
        }
        return basePath + projectPackage + mainPath + packageName + file.getPackagePath() + File
                .separator + getTemplateFileName(entityName, file);
    }


    private static String getTemplateFileName(String entityName, TemplateFile file) {
        return entityName + file.getTemplateName();
    }

    private static String getBasePath() {
        return GeneratorConfiguration.projectPath + GeneratorConfiguration.projectName + File.separator;
    }


    /**
     * 模板文件名称
     */
    private String templateName;

    /**
     * 包路径
     */
    private String packagePath;

    TemplateFile(String templateName, String packagePath) {
        this.templateName = templateName;
        this.packagePath = packagePath;
    }

    public String getTemplateName() {
        return templateName;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public static void main(String[] args) {
        System.out.println(getFileName("Vehicle", PAGE));
    }
}
