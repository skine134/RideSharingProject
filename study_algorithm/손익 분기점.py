static_value,dynamic_value,sale_value=map(int, input().split())
total_sale=sale_value-dynamic_value
if(total_sale<=0):
    print(-1)
elif(static_value==total_sale):
    print(1)
else:
    print(int(static_value/total_sale)+1)
