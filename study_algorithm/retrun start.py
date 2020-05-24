def star(num,text):
    s=""
    for i in range(3):
        arr=[]
        for j in range(3):
            if num/3!=1:
                if i==1 and j==1:
                    tmp=star(num/3,' ')
                else:
                    tmp=star(num/3,text)
                if len(arr)==0:
                    arr=tmp.split("\n")
                else:
                    arr2=tmp.split("\n")
                    for k in range(len(arr)):
                        arr[k]+=arr2[k]
            else:
                if i==1 and j==1:
                    s+=' '
                else:
                    s+=text
        if num/3!=1:
            for k in range(len(arr)):
                s+="".join(arr[k])
                s+="\n"
            s=s[:len(s)-1]
        else:
            s+="\n"
    return s
inputdata=int(input())
print(star(inputdata,'*'))

