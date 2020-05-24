using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class Xattack : MonoBehaviour
{
    //쿨타임이 있고 레벨에 따라 추가골드

    private state status;
    public string xattack;
    public Monsterstate monster;
    public Animator anim;

    public Image image;
    public GameObject effect;

    public Button button;
    public Text text;

    public GameObject levelImage;

    public float coolTime = 10.0f;
    public bool isClicked = false;

    float leftTime = 10.0f;
    float speed = 5.0f;

    public int level=0;
    public int damage = 10;
    void Update()
    {
        XattackText();
        if (level > 0)
        {
            levelImage.SetActive(false);
        }
        if (isClicked)
            if (leftTime > 0)
            {
                leftTime -= Time.deltaTime * speed;
                if (leftTime < 0)
                {
                    leftTime = 0;
                    if (button)
                        button.enabled = true;
                    isClicked = false;
                }

                float ratio = (leftTime / coolTime);
                if (image)
                    image.fillAmount = ratio;
                if (ratio < 0.82f)
                {
                    effect.SetActive(false);
                }
            }
    }
    private void Awake()
    {
        status = GameObject.Find("status").GetComponent<state>();
    }
    public void StartCoolTime()
    {
        if (level > 0)
        {
            monster.DamagedHp(status.Getattack()*2);
            status.AddGold(status.getGoldPer() * level * damage);
            leftTime = coolTime;
            isClicked = true;
            effect.SetActive(true);
            anim.SetTrigger("Xattack");
            GameObject.Find("Monster").GetComponent<Animator>().SetTrigger("Attacked");
            if (button)
                button.enabled = false; // 버튼 기능을 해지함.
        }
       
    }
    public void XattackText()
    {
        text.text = "x자 베기: 레벨- " + level;
    }   
}
