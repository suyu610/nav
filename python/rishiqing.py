import requests
import json
from flask import Flask
from flask import request
from gevent import pywsgi
import urllib.parse
import time
import os
queryUrl = "https://www.rishiqing.com/task/v1/todo/filterList"
loginUrl = "https://www.rishiqing.com/task/j_spring_security_check"
myLoginUrl = "http://localhost:18814/checkToken"
# 2021.05.21
def getNowDateFormate():
	return time.strftime("%Y.%m.%d", time.localtime()) 

# 从文本中读cookie,并转为dict形式
def readCookieInFile():
	fileName = 'cookie.txt'	
	try:
		with open("cookie.txt",mode="r+",encoding="utf-8") as f:
			if(os.path.getsize(fileName) == 0):
				cookieStr = login("username","password")
				f.write(cookieStr)
				print(cookieStr)		
				return json.loads(cookieStr)
			else:
				cookieStr = f.readlines()[0]		
				f.close()		
				return json.loads(cookieStr)
	except FileNotFoundError:
		file = open(fileName,'w')
		file.close()
		readCookieInFile()
			
		
def getList():		
		url_params = {"endDate": getNowDateFormate(),"sort": "customSort","startDate": getNowDateFormate()}
		r = requests.post(queryUrl,params = url_params,cookies=readCookieInFile())
		# 检验cookie的有效
		# {"code":401000,"message":"未获取到用户信息，请重新登录","loginStatus":"fail"}
		text = json.loads(r.text)
		if("message" in text):
			login("username","password")
			getList()
		else:
			return r.text

def login(username,password):
	url_params = {"j_username": username,"j_password": password,"remember-me": "true"}	
	s = requests.Session()
	c = requests.cookies.RequestsCookieJar()
	s.cookies.update(c)

	r = s.post(loginUrl,params = url_params)
	cookie = requests.utils.dict_from_cookiejar(s.cookies)
	return json.dumps(cookie)	

def validateToken(token):
	r = requests.get(myLoginUrl,params = {"token":token})
	print(r.text)

app = Flask(__name__)

@app.route('/', methods=['GET', 'POST'])
def home():	
	return getList()

if __name__ == '__main__':    
	server = pywsgi.WSGIServer(('0.0.0.0', 6886), app)
	server.serve_forever()
	app.run()