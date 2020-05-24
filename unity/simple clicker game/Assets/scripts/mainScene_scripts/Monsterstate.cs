using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Monsterstate : MonoBehaviour
{
    private int HP;
    private string NAME;
    private int DEPENCE;
    private int GOLD;
    private int MaxHp = 10;
    private const int MinHp = 0;
    public GameObject hudDamageText;
    private Transform Hud;
    private Image HpImage;
    public static Monsterstate instance = null;
    private int pre_Hp;
    private state character;
    public void DamagedHp(int damage)
    {
        Hud = GameObject.Find("Monster").transform.Find("Hud");
        character = GameObject.Find("status").GetComponent<state>();

        pre_Hp = this.HP;
        if (HP >= MinHp)
        {
            this.HP = HP - damage;
        }
        else
            this.HP = MinHp;
        if (HP < 0)
        {
            this.HP = MaxHp+10;
            pre_Hp = this.HP;
            MaxHp = this.HP;
            GameObject monster = GameObject.Find("Monster");
            monster.GetComponent<SpriteRenderer>().sprite = Resources.Load<Sprite>("Static\\Side\\Megpack III Fallen Kings Undead King Berthelot");
            HpImage.fillAmount = 1;
            character.AddGold(this.GOLD);

        }
        GameObject hudText = Instantiate(hudDamageText);
        hudText.GetComponent<DamageText>().damage = damage;
        hudText.SetActive(true);
        hudText.transform.position = Hud.position;

    }
    public void AutoBattleHeal(int ABH)
    {
        if (HP <= MaxHp)
        {
            if (HP + ABH < MaxHp)
            {
                this.HP = HP + ABH;
            }
            else
                this.HP = MaxHp;

        }
    }
    public int hp
    {
        get { return this.HP; }
        set { this.HP = value; }
    }
    public string Name
    {
        get { return this.NAME; }
        set { this.NAME = value; }
    }
    public int depence
    {
        get { return this.DEPENCE; }
        set { this.DEPENCE = value; }
    }
    public int gold
    {
        get { return this.GOLD; }
        set { this.GOLD = value; }

    }
    
    public Monsterstate(){ }
    public Monsterstate(int hp,string name,int depence,int gold)
    {
        this.HP = hp;
        this.NAME = name;
        this.DEPENCE = depence;
        this.GOLD = gold;
    }
    private void Awake()
    {
        if (instance == null)
        {
            instance = this;
        }
        else
        {
            Destroy(gameObject);
        }
        this.HP = 10;
        this.GOLD = 100;
        pre_Hp = this.HP;
        HpImage = GameObject.Find("Monster").transform.Find("hp_glass").Find("hp").gameObject.GetComponent<Image>();
        HpImage.fillAmount = pre_Hp/MaxHp;
    }

    private void Start()
    {
        DontDestroyOnLoad(gameObject);
    }
    private void Update()
    {
        float pre_damaged = (float)this.pre_Hp / MaxHp;
        float damageper = (float)this.HP / MaxHp;
        if (GameObject.Find("Monster") != null)
        {
            HpImage = GameObject.Find("Monster").transform.Find("hp_glass").Find("hp").gameObject.GetComponent<Image>();
            HpImage.fillAmount = Mathf.Lerp(HpImage.fillAmount, damageper, Time.deltaTime*5);
        }
    }
}
