package com.example.mystore.data

val sampleProducts = listOf(
    Product(
        id = 1,
        name = "بطة 3D",
        description = "نموذج تجريبي لاختبار العرض ثلاثي الأبعاد. جرّب سحب الشاشة لتدويره بحرية!",
        price = 100.0,
        category = "نماذج",
        modelPath = "https://modelviewer.dev/shared-assets/models/Astronaut.glb"
    ),
    Product(
        id = 2,
        name = "رائد فضاء",
        description = "نموذج ثانٍ لتجربة السحب بين السلع. اسحب للأعلى للسلعة التالية.",
        price = 250.0,
        category = "نماذج",
        modelPath = "https://modelviewer.dev/shared-assets/models/NeilArmstrong.glb"
    ),
    Product(
        id = 3,
        name = "كرة عاكسة",
        description = "نموذج ثالث لمقارنة أنواع الملفات. جميع النماذج تدور بحرية.",
        price = 150.0,
        category = "نماذج",
        modelPath = "https://modelviewer.dev/shared-assets/models/reflective-sphere.gltf"
    )
)
