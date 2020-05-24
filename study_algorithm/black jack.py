card, sel_sum=map(int, input().split())
card_num=list(map(int, input().split()))
max_count=0
for i in range(card):
    count=card_num[i]
    for j in range(i+1,card):
        j_count=count+card_num[j]
        for k in range(j+1,card):
            if j_count+card_num[k]>sel_sum:
                continue
            else:
                k_count=j_count+card_num[k]
                if max_count<=k_count:
                    max_count=k_count
            if max_count==sel_sum:
                    break
        if max_count==sel_sum:
                    break
    if max_count==sel_sum:
        break
print(max_count)
