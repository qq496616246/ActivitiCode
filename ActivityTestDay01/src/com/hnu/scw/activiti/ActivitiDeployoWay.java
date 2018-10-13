package com.hnu.scw.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * @author scw
 * @create 2018-01-15 14:11
 * @desc Activiti的部署方法
 **/
public class ActivitiDeployoWay {
    /**
     * 通过bpmn和png资源进行部署
     */
    @Test
    public void testDeployFromClasspath(){
        //得到流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getRepositoryService()
                .createDeployment()
                .addClasspathResource("shenqing.bpmn")
                .addClasspathResource("shenqing.png")
                .deploy();
    }

    /**
     * 通过 inputstream完成部署
     */
    @Test
    public void testDeployFromInputStream(){
        InputStream bpmnStream = this.getClass().getClassLoader().getResourceAsStream("shenqing.bpmn");
        //得到流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getRepositoryService()
                .createDeployment()
                .addInputStream("shenqing.bpmn", bpmnStream)
                .deploy();
    }

    /**
     * 通过zipinputstream完成部署
     * 注意：这个的话，需要将bpmn和png文件进行压缩成zip文件，然后放在项目src目录下即可(当然其他目录也可以)
     */
    @Test
    public void testDeployFromZipinputStream(){
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("shenqing.zip");
        ZipInputStream zipInputStream = new ZipInputStream(in);
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getRepositoryService()
                .createDeployment()
                .addZipInputStream(zipInputStream)
                .deploy();
    }

    /**
     * 删除已经部署的Activiti流程
     */
    @Test
    public void testDelete(){
        //得到流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //第一个参数是部署的流程的ID，第二个true表示是进行级联删除
        processEngine.getRepositoryService()
                .deleteDeployment("701",true);
    }

    /**
     * 查询所有的部署流程
     */
    @Test
    public void queryAllDeplyoment(){
        //得到流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<Deployment> lists = processEngine.getRepositoryService()
                .createDeploymentQuery()
                .orderByDeploymenTime()//按照部署时间排序
                .desc()//按照降序排序
                .list();
        for (Deployment deployment:lists) {
            System.out.println(deployment.getId() +"    部署名称" + deployment.getName());
        }
    }
    /**
     * 根据名称查询流程部署
     */
    @Test
    public void testQueryDeploymentByName(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<Deployment> deployments = processEngine.getRepositoryService()
                .createDeploymentQuery()
                .orderByDeploymenTime()//按照部署时间排序
                .desc()//按照降序排序
                .deploymentName("请假流程")
                .list();
        for (Deployment deployment : deployments) {
            System.out.println(deployment.getId());
        }
    }
    /**
     * 查询所有的流程定义
     */
    @Test
    public void testQueryAllPD(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<ProcessDefinition> pdList = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion()
                .desc()
                .list();
        for (ProcessDefinition pd : pdList) {
            System.out.println(pd.getName());
        }
    }
    /**
     * 查看流程图
     * 根据deploymentId和name(在act_ge_bytearray数据表中)
     */
    @Test
    public void testShowImage() throws Exception{
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        InputStream inputStream = processEngine.getRepositoryService()
                /**
                 * deploymentID
                 * 文件的名称和路径
                 */
                .getResourceAsStream("801","shenqing.png");
        OutputStream outputStream3 = new FileOutputStream("e:/processimg.png");
        int b = -1 ;
        while ((b=inputStream.read())!=-1){
            outputStream3.write(b);
       }
        inputStream.close();
        outputStream3.close();
    }
    /**
     * 根据pdid查看图片(在act_re_procdef数据表中)
     * @throws Exception
     */
    @Test
    public void testShowImage2() throws Exception{
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        InputStream inputStream = processEngine.getRepositoryService()
                .getProcessDiagram("shenqing:1:804");
        OutputStream outputStream = new FileOutputStream("e:/processimg.png");
        int b = -1 ;
        while ((b=inputStream.read())!=-1){
            outputStream.write(b);
        }
        inputStream.close();
        outputStream.close();
    }
    /**
     * 查看bpmn文件(在act_re_procdef数据表中)
     */
    @Test
    public void testShowBpmn() throws Exception{
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        InputStream inputStream = processEngine.getRepositoryService()
                .getProcessModel("shenqing:1:804");
        OutputStream outputStream = new FileOutputStream("e:/processimg.bpmn");
        int b = -1 ;
        while ((b=inputStream.read())!=-1){
            outputStream.write(b);
        }
        inputStream.close();
        outputStream.close();
    }
}
