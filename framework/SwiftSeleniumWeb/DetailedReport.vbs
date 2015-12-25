CALL SendMailOutlook("Tripti.Mittal@mastek.com;Chandra.Reddy@mastek.com", "Swift Framework Detailed Report", "PFA the Detailed Report", "D:\Tripti Docs\workspace\SwiftSeleniumV3\Resources\Results\DetailedResults.csv")

Function SendMailOutlook(SendTo, Subject, Body, Attachment)
  'strMailto, Subject, Message, strMailfrom,strAttach
 Set ol=CreateObject("Outlook.Application")
    Set Mail=ol.CreateItem(0)
    Mail.to=SendTo
    Mail.Subject=Subject
    Mail.Body=Body
    If (Attachment <> "") Then
        Mail.Attachments.Add(Attachment)
    End If
    Mail.Send
'    ol.Quit
    Set Mail = Nothing
    Set ol = Nothing
End Function
 
