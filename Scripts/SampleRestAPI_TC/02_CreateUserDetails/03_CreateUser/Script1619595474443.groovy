import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import groovy.json.JsonSlurper as JsonSlurper
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.google.common.base.Verify as Verify

response = WS.sendRequest(findTestObject('SampleRestAPI/CreateUser/CreateUser', [('url') : GlobalVariable.url]))

WS.verifyResponseStatusCode(response, responseCode)

Verify.verify(response.getStatusCode().equals(responseCode))

WS.verifyElementPropertyValue(response, 'name', name)

WS.verifyElementPropertyValue(response, 'job', job)

String result  = response.getResponseText()

println (".........Json Response is : "+result)

JsonSlurper js = new JsonSlurper()

Map m = js.parseText(result)

println(".....Created User Details......." +m.entrySet())

Boolean flag = m.containsKey("id")
if(flag) {
	println(".....id is created as " +m.get("id"))
}
else{
	println(".....id is not created ")
}


