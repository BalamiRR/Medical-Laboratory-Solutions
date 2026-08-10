db = db.getSiblingDB('notesdb');

db.notes.insertMany([
  {
    patientId: 1,
    patient: "TestNone",
    note: "Le patient déclare qu'il 'se sent très bien'\nPoids égal ou inférieur au poids recommandé",
    createdAt: new Date()
  },
  {
    patientId: 2,
    patient: "TestBorderline",
    note: "Le patient déclare qu'il ressent beaucoup de stress au travail\nIl se plaint également que son audition est anormale dernièrement",
    createdAt: new Date()
  },
  {
    patientId: 2,
    patient: "TestBorderline",
    note: "Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois\nIl remarque également que son audition continue d'être anormale",
    createdAt: new Date()
  },
  {
    patientId: 3,
    patient: "TestInDanger",
    note: "Le patient déclare qu'il fume depuis peu",
    createdAt: new Date()
  },
  {
    patientId: 3,
    patient: "TestInDanger",
    note: "Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière\nIl se plaint également de crises d'apnée respiratoire anormales\nTests de laboratoire indiquant un taux de cholestérol LDL élevé",
    createdAt: new Date()
  },
  {
    patientId: 4,
    patient: "TestEarlyOnset",
    note: "Le patient déclare qu'il lui est devenu difficile de monter les escaliers\nIl se plaint également d'être essoufflé\nTests de laboratoire indiquant que les anticorps sont élevés\nRéaction aux médicaments",
    createdAt: new Date()
  },
  {
    patientId: 4,
    patient: "TestEarlyOnset",
    note: "Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps",
    createdAt: new Date()
  },
  {
    patientId: 4,
    patient: "TestEarlyOnset",
    note: "Le patient déclare avoir commencé à fumer depuis peu\nHémoglobine A1C supérieure au niveau recommandé",
    createdAt: new Date()
  },
  {
    patientId: 4,
    patient: "TestEarlyOnset",
    note: "Taille, Poids, Cholestérol, Vertige et Réaction",
    createdAt: new Date()
  }
]);