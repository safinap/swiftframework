                'CommandButton1_Click(sWSDL_URL)
                'sWSDL_URL = "http://172.16.244.237:8380/BOPWS_CXF/rest/ExternalWS/bopTransactions/GETQUOTE" 
				 sWSDL_URL=WScript.Arguments.Item(0)
				 sRequestXML=WScript.Arguments.Item(1)
				'sRequestXML = "D:\BID_TEST\BID_Webservices_xmlfiles\\Quote_Request.xml"				
                'sWebSerReq_URL = "http://172.16.244.152:8380/BOPWS_CXF/rest/ExternalWS"
                sContentType = "application/xml"
                On Error Resume Next
                Set oWinHTTP = CreateObject("WinHttp.WinHttpRequest.5.1")
                Set oXMLDOM = CreateObject("Microsoft.xmldom")
                oXMLDOM.Load (sRequestXML)
                oWinHTTP.SetTimeOuts 60000, 60000, 60000, 60000
                oWinHTTP.Open "POST", sWSDL_URL, False
                oWinHTTP.SetRequestHeader "Content-Type", sContentType
                'WinHTTP.SetRequestHeader "SOAPAction", sWebSerReq_URL
                oWinHTTP.Send oXMLDOM
				'MsgBox oWinHTTP.ResponseText
				dim filesys, demofolder, filetxt 
				Set filesys = CreateObject("Scripting.FileSystemObject") 
				Set demofolder = filesys.GetFolder("D:\projects\") 
				Set filetxt = demofolder.CreateTextFile("PolicyListResponse.xml", True) 
				filetxt.WriteLine(oWinHTTP.ResponseText) 
				filetxt.Close 
				
                'MsgBox Err.Description
                
				
