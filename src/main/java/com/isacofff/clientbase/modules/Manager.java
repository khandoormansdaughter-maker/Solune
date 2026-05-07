package com.isacofff.clientbase.modules;

import com.isacofff.clientbase.modules.features.ArmorHUD;
import com.isacofff.clientbase.modules.features.AutoEat;
import com.isacofff.clientbase.modules.features.AutoWalk;
import com.isacofff.clientbase.modules.features.BetterSprint;
import com.isacofff.clientbase.modules.features.BiomeDisplay;
import com.isacofff.clientbase.modules.features.ClickGui;
import com.isacofff.clientbase.modules.features.CoordinatesDisplay;
import com.isacofff.clientbase.modules.features.CPSCounter;
import com.isacofff.clientbase.modules.features.CrystalHelper;
import com.isacofff.clientbase.modules.features.DirectionHUD;
import com.isacofff.clientbase.modules.features.FPSDisplay;
import com.isacofff.clientbase.modules.features.Keystrokes;
import com.isacofff.clientbase.modules.features.LightLevel;
import com.isacofff.clientbase.modules.features.PearlPredictor;
import com.isacofff.clientbase.modules.features.Perspective;
import com.isacofff.clientbase.modules.features.PingDisplay;
import com.isacofff.clientbase.modules.features.PotionHUD;
import com.isacofff.clientbase.modules.features.SaturationDisplay;
import com.isacofff.clientbase.modules.features.SpeedrunTimer;
import com.isacofff.clientbase.modules.features.TargetInfo;
import com.isacofff.clientbase.modules.features.ToggleSprint;
import com.isacofff.clientbase.modules.features.Zoom;
import com.isacofff.clientbase.Category;

import java.util.ArrayList;

public class Manager {

    public final ArrayList<Module> modules = new ArrayList<>();
    //Add the modules here for them to appear in the ClickGui
    public void init() {
    modules.add(new ClickGui());
    modules.add(new FPSDisplay());
    modules.add(new CoordinatesDisplay());
    modules.add(new ToggleSprint());
    modules.add(new ArmorHUD());
    modules.add(new CPSCounter());
    modules.add(new Keystrokes());
    modules.add(new PotionHUD());
    modules.add(new DirectionHUD());
    modules.add(new AutoWalk());
    modules.add(new Zoom());
    modules.add(new Perspective());
    modules.add(new TargetInfo());
    modules.add(new SpeedrunTimer());
    modules.add(new AutoEat());
    modules.add(new BetterSprint());
    modules.add(new PingDisplay());
    modules.add(new SaturationDisplay());
    modules.add(new BiomeDisplay());
    modules.add(new LightLevel());
    modules.add(new CrystalHelper());
    modules.add(new PearlPredictor());
    }

    public void onTick() {
        for (Module m : modules) {
            if (m.isEnabled()) {
                m.onUpdate();
            }
        }
    }

    public void onRender() {
        for (Module m : modules) {
            if (m.isEnabled()) {
                m.onRender();
            }
        }
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public <T extends Module> T getModule(Class<T> classs) {

        for (Module m : modules) {
            if (classs.isInstance(m)) return classs.cast(m);
        }
        return null;
    }

    public Module getModuleByName(String name) {
        for (Module m : modules) {
            if (m.getName().equalsIgnoreCase(name)) return m;
        }
        return null;
    }

    public ArrayList<Module> getModulesByCategory(Category c) {
        ArrayList<Module> list = new ArrayList<>();
        for (Module m : modules) {
            if (m.getCategory() == c) list.add(m);
        }
        return list;
    }
}
