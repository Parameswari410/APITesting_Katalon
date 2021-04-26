import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import internal.GlobalVariable as GlobalVariable

response = WS.sendRequestAndVerify(findTestObject('RestAPI/ListUsers'))

WS.verifyElementsCount(response, 'data.id', 6)

notFoundResponse = WS.sendRequestAndVerify(findTestObject('RestAPI/SingleUserNotFound'))

WS.verifyResponseStatusCodeInRange(notFoundResponse, 400, 404)

createResponse = WS.sendRequestAndVerify(findTestObject('RestAPI/CreateUser'))

getUserResponse = WS.sendRequestAndVerify(findTestObject('RestAPI/GetSingleUser'))

firstName = WS.getElementPropertyValue(getUserResponse, 'data.first_name')

println(' ... Property value is : ' + firstName)

GlobalVariable.firstName = firstName

println(' ... Global variable name set as : ' + GlobalVariable.firstName)

updateResponse = WS.sendRequestAndVerify(findTestObject('RestAPI/UpdateUser'))

not_run: WS.verifyElementPropertyValue(updateResponse, 'data.first_name', GlobalVariable.firstName)

WS.sendRequestAndVerify(findTestObject('RestAPI/DeleteUser'))

