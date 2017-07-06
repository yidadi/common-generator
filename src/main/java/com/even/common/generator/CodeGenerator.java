package com.even.common.generator;

import com.even.common.generator.entity.TableProperty;
import com.even.common.generator.entity.TablePropertyParser;
import com.even.common.generator.enums.TemplateFile;
import com.even.common.generator.enums.VelocityContextKey;
import com.even.common.generator.util.GeneratorConfiguration;
import com.even.common.generator.util.TablePropertyUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Properties;


/**
 * 代码生成器
 *
 * @author yidadi
 * @date 2016-09-04 22：27
 */
public class CodeGenerator {

    private static  final Logger LOGGER = LoggerFactory.getLogger(CodeGenerator.class);

    private static final  String UTF_8 = "UTF-8";

    public static void main(String[] args) throws  Exception{
        List<String> entities = GeneratorConfiguration.entityObjects;
        List<String> tableComments = TablePropertyUtils.getTableComments(entities, GeneratorConfiguration.dbName);
        TemplateFile[] values = TemplateFile.values();
        Velocity.init(getProperties());
        VelocityContext context = new VelocityContext();
        for (int i = 0,size=entities.size(); i <size ; i++) {
            String e = entities.get(i);
            initVelocityContext(context,e,tableComments.get(i));
            for (int j = 0,len = values.length; j < len; j++) {
                TemplateFile file = values[j];
                Template template = Velocity.getTemplate("/template/"+ file.getTemplateName(), UTF_8);
                String fileName = TemplateFile.getFileName(e,file);
                createTemplateFile(context, template, fileName);
            }   
        }

    }

    private static void initVelocityContext(VelocityContext context,String entityName,String tableComment) {
        context.put(VelocityContextKey.UPPER_ENTITY.getKey(),entityName);
        context.put(VelocityContextKey.LOWER_ENTITY.getKey(), WordUtils.uncapitalize(entityName));
        String tableName = TablePropertyUtils.formatTableName(entityName);
        context.put(VelocityContextKey.TABLE_NAME.getKey(),tableName);
        List<TableProperty> propertyList = TablePropertyUtils.getPropertyList(GeneratorConfiguration.dbName, tableName);
        context.put(VelocityContextKey.PROPERTY_LIST.getKey(),propertyList);
        context.put(VelocityContextKey.PARSER.getKey(),new TablePropertyParser(propertyList));
        context.put(VelocityContextKey.ENTITY_COMMENT.getKey(),tableComment);
        context.put(VelocityContextKey.PROJECT_NAME.getKey(),GeneratorConfiguration.projectName);
        context.put(VelocityContextKey.COMPANY_NAME.getKey(),GeneratorConfiguration.companyName);
        context.put(VelocityContextKey.UPPER_PROJECT_NAME.getKey(),WordUtils.uncapitalize(GeneratorConfiguration
                .projectName));
    }

    private static void createTemplateFile(VelocityContext context, Template template, String fileName) throws IOException {
        File saveFile = new File(fileName);
        // 如果当前文件的父路径没有,就创建
        if (!saveFile.getParentFile().exists()) {
            saveFile.getParentFile().mkdirs();
        }
        // 如果代码已经存在,不要覆盖
        if (!saveFile.exists()) {
            FileOutputStream outStream = new FileOutputStream(saveFile);
            OutputStreamWriter writer = new OutputStreamWriter(
                    outStream, UTF_8);// 必须设置编码格式
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            template.merge(context, bufferWriter);
            bufferWriter.flush();
            outStream.close();
            bufferWriter.close();
        }
        LOGGER.info("代码已经生成完成,请检查代码!");
    }

    private static Properties getProperties() {
        Properties p = new Properties();
        p.put("file.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        p.setProperty(Velocity.OUTPUT_ENCODING, UTF_8);
        p.setProperty(Velocity.INPUT_ENCODING, UTF_8);
        return p;
    }
}
