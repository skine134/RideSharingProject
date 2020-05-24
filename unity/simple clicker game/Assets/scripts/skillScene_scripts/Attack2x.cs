using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class Attack2x : MonoBehaviour
{
    public state status;

    public string attack2x;


    public Image image;
    public Image fireIcon;

    public GameObject effect;
    public Animator anim;

    public GameObject levelImage;

    public Button button;
    public Text text;
    public float coolTime = 10.0f;
    public bool isClicked = false;
    float leftTime = 10.0f;
    float checkTime = 10.0f;
    float speed = 5.0f;

    float check;
    public float keepPoint = 10.0f;
    bool isCheck = false;

    public int level = 0;
    int baceGoldPer;
    int powerGoldPer;

    void Update()
    {
        Attack2xText();
        if (level > 0)
        {
            levelImage.SetActive(false);
        }
        if (isClicked)
            if (leftTime > 0)
            {
                leftTime -= Time.deltaTime * speed;
                checkTime -= Time.deltaTime * speed;
                if (leftTime < 0)
                {
                    leftTime = 0;
                    if (button)
                        button.enabled = true;
                    isClicked = false;
                }

                float ratio = (leftTime / coolTime);
                float keepratio = checkTime / (coolTime - check);
                if (image)
                    image.fillAmount = ratio;
                if (fireIcon)
                {
                    fireIcon.fillAmount = keepratio;
                }
                if (keepratio < 0)
                {
                    anim.SetBool("Attack 2x", false);
                    effect.SetActive(false);
                }

                KeepTimeCheck();
            }

    }
    public void SetGoldPer()
    {
        baceGoldPer = status.getGoldPer();
        status.setGoldPer(status.getGoldPer() * 2);
        powerGoldPer = status.getGoldPer();
    }
    public void SetKeepTime()
    {
        if (keepPoint >= 10)
        {
            keepPoint = 10;
        }
        else if (keepPoint <= 0)
        {
            keepPoint = 0;
        }
        checkTime = coolTime * (keepPoint / 10);
        check = coolTime - checkTime;
        isCheck = true;
    }
    public void StartCoolTime()
    {
        if (level > 0)
        {
            leftTime = coolTime;
            SetGoldPer();
            SetKeepTime();
            isClicked = true;
            effect.SetActive(true);
            anim.SetBool("Attack 2x", true);
            if (button)
                button.enabled = false; // 버튼 기능을 해지함.
        }
    }


    public void KeepTimeCheck()
    {
        if (isCheck)
        {
            if (check >= leftTime)
            {
                status.setGoldPer(status.getGoldPer() - (powerGoldPer - baceGoldPer));
                isCheck = false;
            }
        }
    }
    public void Attack2xText()
    {
        text.text = "공격력 2배 버프- " + level + "현재 GoldPer: " + status.getGoldPer();

    }
}
