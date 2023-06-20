package com.example.application.views.teams;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Background;
import com.vaadin.flow.theme.lumo.LumoUtility.BorderRadius;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.FontWeight;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.Overflow;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;
import com.vaadin.flow.theme.lumo.LumoUtility.Width;

import javax.swing.*;
import java.util.Collection;

public class TeamsViewCard extends ListItem {

    public TeamsViewCard(String text, String url, String subtitleText, String descriptionText, String cardDescriptionText) {
        addClassNames(Background.CONTRAST_5,
                Display.FLEX,
                FlexDirection.COLUMN,
                AlignItems.START,
                Padding.MEDIUM,
                BorderRadius.LARGE);

        Div div = new Div();
        div.addClassNames(Background.CONTRAST,
                Display.FLEX,
                AlignItems.CENTER,
                JustifyContent.CENTER,
                Margin.Bottom.MEDIUM,
                Overflow.HIDDEN,
                BorderRadius.MEDIUM,
                Width.FULL);
        div.setHeight("160px");

        Image image = new Image();
        image.setWidth("100%");
        image.setSrc(url);
        image.setAlt(text);

        div.add(image);

        Span header = new Span();
        header.addClassNames(FontSize.XLARGE,
                FontWeight.SEMIBOLD);
        header.setText(text);

        Span subtitle = new Span();
        subtitle.addClassNames(FontSize.SMALL,
                TextColor.SECONDARY);
        subtitle.setText(subtitleText);

        Paragraph description = new Paragraph(descriptionText);
        description.addClassName(Margin.Vertical.MEDIUM);

        add(div, header, subtitle, description);

        addClickListener(event -> {
            Dialog dialog = new Dialog();
            dialog.setCloseOnOutsideClick(true);

            H2 cardHeader = new H2(text);
            Span cardSubtitle = new Span(subtitleText);
            Paragraph cardDescription = new Paragraph(cardDescriptionText);

            dialog.add(cardHeader, cardSubtitle, cardDescription);

            dialog.open();
        });
    }
}
