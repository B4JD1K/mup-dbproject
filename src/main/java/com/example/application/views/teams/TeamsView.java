package com.example.application.views.teams;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.charts.model.Global;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;
import com.vaadin.flow.theme.lumo.LumoUtility.ListStyleType;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.MaxWidth;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;
import jakarta.annotation.security.PermitAll;

import java.sql.*;

@PageTitle("Teams")
@Route(value = "teams", layout = MainLayout.class)
@AnonymousAllowed
public class TeamsView extends Main implements HasComponents, HasStyle {

    private OrderedList imageContainer;

    public TeamsView() {
        constructUI();


        imageContainer.add(new TeamsViewCard(
                "Astralis",
                "https://cybersport.pl/wp-content/uploads/2020/10/astralis_wallpaper20.jpg",
                "Najlepsza drużyna wszechczasów w historii CS:GO",
                "Duńska profesjonalna drużyna e-sportowa, znana głównie ze swojej taktycznej dominacji na wielu mistrzostwach w CS:GO.",
                "Astralis jest powszechnie uznawana za najlepszą drużynę w historii Counter-Strike: Global Offensive (CS:GO). Ich osiągnięcia, dominacja i wpływ na świat e-sportu czynią ich niezrównanymi w tej dyscyplinie. Drużyna Astralis zrewolucjonizowała sposób gry w CS:GO, wprowadzając nowe standardy i strategie, które na zawsze zmieniły krajobraz profesjonalnych rozgrywek.\n" +
                        "\n" +
                        "Co czyni Astralis tak wyjątkowymi? To połączenie niezwykłej indywidualnej klasy zawodników, doskonałego zgrania, zdolności taktycznych i doskonałej pracy zespołowej. Ich skład wyróżnia się nie tylko umiejętnościami strzeleckimi, ale także niezawodnym podejmowaniem decyzji w kluczowych momentach, umiejętnością adaptacji do zmieniającej się sytuacji i zdolnością do współpracy na najwyższym poziomie.\n" +
                        "\n" +
                        "Astralis odniosła niezliczone sukcesy, zwyciężając w najważniejszych turniejach i zdobywając wiele tytułów mistrzowskich. Ich styl gry, precyzja i zrozumienie gry są bezprecedensowe. Drużyna potrafi zaskakiwać przeciwników innowacyjnymi taktykami i wykorzystywać każdą słabość rywali.\n" +
                        "\n" +
                        "Ich wpływ na e-sport jest ogromny. Astralis nie tylko dominuje na arenie zawodowej, ale również inspiruje kolejne pokolenia graczy, zachęcając ich do podążania za marzeniami i dążenia do doskonałości. Są wzorem profesjonalizmu, dyscypliny i poświęcenia, które są niezbędne do osiągnięcia sukcesu na najwyższym poziomie.\n" +
                        "\n" +
                        "Astralis - to nie tylko drużyna, to legenda w świecie CS:GO, która na zawsze pozostanie w pamięci jako najlepsza drużyna wszechczasów."
        ));
        imageContainer.add(new TeamsViewCard(
                "NaVi",
                "https://wallpapercave.com/wp/wp10531533.jpg",
                "Od 1.6 po CS:GO, NaVi to zawsze topowa drużyna.",
                "Rosyjska profesjonalna drużyna e-sportowa, znana z od czasów CS 1.6 gdzie odnosiła liczne sukcesy, a obecnie walczy w rozgrywkach CS:GO.",
                "NaVi (Natus Vincere) to zespół, który uważany jest za jedną z najlepszych drużyn w historii Counter-Strike: Global Offensive (CS:GO). Rosyjska organizacja e-sportowa zdobyła sławę dzięki swojej niezrównanej dominacji na arenie profesjonalnych rozgrywek. Od samego początku istnienia, NaVi wykazała się wybitnymi umiejętnościami strzeleckimi, strategicznymi decyzjami i zdolnościami taktycznymi.\n" +
                        "\n" +
                        "Co wyróżnia NaVi? To ich zdolność do wykonania niewiarygodnych akcji, wygrania trudnych sytuacji i dostarczania niezapomnianych momentów na scenie e-sportowej. Ich skład zawsze był złożony z graczy o wyjątkowym talentu, którzy potrafią współpracować i działać jak zgrana jednostka. NaVi zaskakuje przeciwników swoją nieprzewidywalnością i nieustannym doskonaleniem strategii.\n" +
                        "\n" +
                        "Drużyna odniosła liczne sukcesy, wygrywając wiele prestiżowych turniejów i zdobywając tytuły mistrzowskie. Ich styl gry jest oparty na agresywnym podejściu, szybkich i celnych strzałach oraz zdolności do kontrolowania sytuacji na mapie.\n" +
                        "\n" +
                        "NaVi to nie tylko drużyna, ale również symbol rosyjskiej siły i e-sportowego talentu. Ich wpływ na CS:GO jest niezaprzeczalny, a ich osiągnięcia i umiejętności są nadal inspiracją dla wielu młodych graczy na całym świecie."
        ));
        imageContainer.add(new TeamsViewCard(
                "Vitality",
                "https://wallpaperaccess.com/full/4599698.jpg",
                "Mistrzowie z Wybrańcem odwracającym przebieg meczy",
                "Niezachwiana determinacja i strategiczne genialność oraz \"The Coosen One\" czynią z Vitality potęgę, z którą trzeba się liczyć.",
                "Vitality to francuska drużyna e-sportowa, która jest uważana za jedną z najlepszych w historii Counter-Strike: Global Offensive (CS:GO). Drużyna ta osiągnęła ogromny sukces dzięki swoim umiejętnościom strzeleckim, taktycznemu podejściu i zdolności do adaptacji do zmieniających się warunków gry.\n" +
                        "\n" +
                        "Co wyróżnia Vitality? To głównie genialne występy ich gwiazd, w tym jednego z najbardziej utalentowanych graczy w historii CS:GO - Mathieu \"ZywOo\" Herbaut. Vitality potrafi wykonywać skomplikowane manewry, odbierać rywalom kontrolę nad grą i zdobywać kluczowe rundy.\n" +
                        "\n" +
                        "Drużyna odniosła znaczące zwycięstwa na najważniejszych turniejach, zdobywając tytuły mistrzowskie i zdobywając uznanie wśród społeczności e-sportowej. Ich styl gry opiera się na agresywnych atakach, precyzyjnych strzałach i zdolności do szybkiego podejmowania decyzji.\n" +
                        "\n" +
                        "Vitality stała się ikoną dla francuskiego e-sportu i dla fanów CS:GO na całym świecie. Ich profesjonalizm, poświęcenie i ciężka praca stały się wzorem dla innych drużyn. Wciąż dążą do doskonałości i inspirują kolejne pokolenia graczy."
        ));
        imageContainer.add(new TeamsViewCard(
                "G2",
                "https://i.pinimg.com/originals/21/38/a3/2138a31b37050769b872d03a78da7ecd.jpg",
                "Wzmocnionienia agresywnym sylem uwalniają nieskrępowaną furię",
                "Agresywny styl gry G2 i nieustające dążenie do zwycięstwa zachwycają publiczność na całym świecie.",
                "G2 to europejska drużyna e-sportowa, która zasłynęła jako jedna z najlepszych w historii Counter-Strike: Global Offensive (CS:GO). Ich dynamiczny styl gry, zdolności taktyczne i wyjątkowe umiejętności indywidualne przyniosły im wiele sukcesów na arenie profesjonalnych rozgrywek.\n" +
                        "\n" +
                        " Co czyni G2 tak wyjątkowymi? To zespół złożony z graczy o niezwykłym talentu, którzy potrafią wykonywać oszałamiające akcje, wprowadzając publiczność w zachwyt. G2 jest znane z agresywnego podejścia do gry, skutecznych ataków i zdolności do podejmowania decyzji w krótkim czasie.\n" +
                        "\n" +
                        "Drużyna odniosła wiele sukcesów, wygrywając prestiżowe turnieje i zdobywając tytuły mistrzowskie. Ich strategie i innowacyjne podejście do gry uczyniły ich jednym z najbardziej respektowanych zespołów w CS:GO.\n" +
                        "\n" +
                        "G2 to nie tylko drużyna, ale również marka, która reprezentuje europejski e-sport na najwyższym poziomie. Ich styl gry, pasja i zobowiązanie do doskonalenia sprawiają, że są inspiracją dla wielu graczy na całym świecie."
        ));
        imageContainer.add(new TeamsViewCard(
                "Heroic",
                "https://esports.gg/_next/image/?url=https%3A%2F%2Fadmin.esports.gg%2Fwp-content%2Fuploads%2F2023%2F01%2FHeroic-Rebrand-968x544.jpeg&w=3840&q=75",
                "Nieugięci, Przekraczający Granice, Inspirujący Wielkością",
                "Wytrwałość i niezłomny duch wynoszą Heroic na bohateryczne szczyty w świecie esportu.",
                "Heroic to duńska drużyna e-sportowa, która jest uważana za jedną z najlepszych w historii Counter-Strike: Global Offensive (CS:GO). Ich sukcesy, profesjonalizm i zdolności do strategicznej gry uczyniły ich niezwykłymi na arenie e-sportowej.\n" +
                        "\n" +
                        "Heroic wyróżnia się doskonałym zgraniem zespołowym, elastycznym podejściem do taktyk i zdolnościami indywidualnymi swoich zawodników. Drużyna ta potrafi wykorzystać słabości przeciwników, tworząc skomplikowane manewry, które często prowadzą do zwycięstwa.\n" +
                        "\n" +
                        "Drużyna odniosła wiele sukcesów, wygrywając ważne turnieje i zdobywając uznanie w społeczności CS:GO. Ich umiejętność analizowania gry i dostosowywania się do zmieniających się warunków czynią ich nieprzewidywalnymi dla rywali.\n" +
                        "\n" +
                        "Heroic to przykład doskonałej organizacji, poświęcenia i determinacji w dążeniu do doskonałości. Ich wpływ na e-sport jest widoczny, a ich osiągnięcia stawiają ich wśród najlepszych drużyn w historii CS:GO."
        ));
        imageContainer.add(new TeamsViewCard(
                "FaZe Clan",
                "https://e1.pxfuel.com/desktop-wallpaper/365/424/desktop-wallpaper-faze-clan-completes-roster-with-two-cs-go-signings-faze-clan-members.jpg",
                "Międzynarodowe umiejętności, styl i niezrównana błyskotliwość",
                "Dynamiczny skład FaZe Clan i innowacyjne strategie posuwają granice rywalizacji w grach kompetencyjnych",
                "FaZe Clan to międzynarodowa drużyna e-sportowa, która jest uważana za jedną z najlepszych w historii Counter-Strike: Global Offensive (CS:GO). Ich styl gry, umiejętności strzeleckie i zdolności taktyczne uczyniły ich jednym z najbardziej respektowanych zespołów na scenie e-sportowej.\n" +
                        "\n" +
                        "FaZe Clan składa się z graczy z różnych krajów, którzy łączą swoje umiejętności, aby tworzyć niezwykłe akcje i strategie. Drużyna ta słynie z dynamicznego i agresywnego stylu gry, który często sprawia, że są nieprzewidywalni dla przeciwników.\n" +
                        "\n" +
                        "Drużyna odniosła wiele sukcesów, wygrywając prestiżowe turnieje i zdobywając tytuły mistrzowskie. Ich umiejętność adaptacji do różnych sytuacji i zdolność do podejmowania trafnych decyzji w kluczowych momentach sprawiają, że są trudni do pokonania.\n" +
                        "\n" +
                        "FaZe Clan to nie tylko drużyna e-sportowa, ale również marka, która przekracza granice esportu. Ich wpływ na społeczność e-sportową jest ogromny, a ich styl gry i kreatywność inspirują innych graczy na całym świecie."
        ));
    }


    private void constructUI() {
        addClassNames("teams-view");
        addClassNames(MaxWidth.SCREEN_LARGE, Margin.Horizontal.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);

        HorizontalLayout container = new HorizontalLayout();
        container.addClassNames(AlignItems.CENTER, JustifyContent.BETWEEN);

        VerticalLayout headerContainer = new VerticalLayout();
        H2 header = new H2("Beautiful photos");
        header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE);
        Paragraph description = new Paragraph("Royalty free photos and pictures, courtesy of Unsplash");
        description.addClassNames(Margin.Bottom.XLARGE, Margin.Top.NONE, TextColor.SECONDARY);
        headerContainer.add(header, description);

        imageContainer = new OrderedList();
        imageContainer.addClassNames(Gap.MEDIUM, Display.GRID, ListStyleType.NONE, Margin.NONE, Padding.NONE);

        container.add(headerContainer);
        add(container, imageContainer);

    }
}

