import math

def bertrand(int_data):
    count=0
    if(int_data==1):
        return 1
    for i in range(int_data+1,2*int_data+1):
        
        if(i%2==0):
            continue
        num=math.sqrt(i)
        for j in range(1,i):
            if(j==1):
                continue
            if(num<j):
                count=count+1
                break
            if(i%j==0):
                break
    return count
input_data=[]
while len(input_data)==0 or input_data[len(input_data)-1]!=0:
    input_data.append(int(input()))
for i in range(len(input_data)):
    if(i==len(input_data)-1):
        break
    print(bertrand(input_data[i]))
