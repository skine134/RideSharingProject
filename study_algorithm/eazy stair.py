def stair(pre_arr,current_digits,max_digits):
    arr=[]
    if current_digits==max_digits:
        for i in range(1,10):
            arr.append(i)
    else:
        for i in range(len(pre_arr)):
            if pre_arr[i]%10!=0:
                arr.append(pre_arr[i]*10+pre_arr[i]%10-1)
            if pre_arr[i]%10!=9:
                arr.append(pre_arr[i]*10+pre_arr[i]%10+1)
    if current_digits!=1:
        arr=stair(arr,current_digits-1,max_digits)
    return arr
N=int(input())
print(len(stair(None,N,N))%1000000000)
