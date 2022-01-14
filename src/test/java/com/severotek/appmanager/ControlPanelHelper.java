package com.severotek.appmanager;

import com.severotek.model.FormData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ControlPanelHelper extends HelperBase {

    public ControlPanelHelper(WebDriver wd) {
        super(wd);
    }

    public void confirmSelection() {
        click(By.xpath("//input[@value='Да, я уверен']"));
    }

    public void pressBtnExecute() {
        click(By.xpath("//button[(text()='Выполнить')]"));
    }

    public void chooseToDeleteEntries() {
        click(By.xpath("//option[@value='delete_selected']"));
    }

    public void actionChoice() {
        click(By.xpath("//select"));
    }

    public void selectCheckBoxFirstEntry() {
        click(By.xpath("//*[@id='result_list']/tbody/tr[1]/td[1]/input"));
    }

    public void pressBtnSave() {
        click(By.xpath("//input[@name='_save']"));
    }

    public void fillEntryForm(FormData formData) {
        type(By.xpath("//input[@id='id_title']"), formData.getTitle());
        type(By.xpath("//input[@id='id_slug']"), formData.getSlug());
        type(By.xpath("//textarea[@id='id_text_markdown']"), formData.getMarkdown());
        type(By.xpath("//textarea[@id='id_text']"), formData.getText());
    }

    public void initAddingEntry() {
        click(By.xpath("//a[@href='/admin/blog/entry/add/']//span[contains(text(),'Добавить')]"));
    }
}
