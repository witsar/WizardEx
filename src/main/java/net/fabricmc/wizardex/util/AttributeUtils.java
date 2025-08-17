package net.fabricmc.wizardex.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;

public class AttributeUtils {
    public static double getAttributeValue(LivingEntity entity, EntityAttribute attribute) {
        EntityAttributeInstance instance = entity.getAttributeInstance(attribute);
        return instance != null ? instance.getValue() : 0.0;
    }
}
