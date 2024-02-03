XMLEncoder:
    XMLEncoder e = new XMLEncoder(new FileOutputStream("jbutton.xml"));
        e.writeObject(new JButton("Hello world"));
        e.close();  
XMLDecoder:
    XMLDecoder d = new XMLDecoder(new FileInputStream("jbutton.xml"));
        obj = d.readObject();
        d.close(); 
//nie mieszać html z kodem bo graficy od html muszą zmieniać kod aplikacji. wygląd niezależny i programista tylko wypełnia  