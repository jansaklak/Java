XMLEncoder:
    XMLEncoder e = new XMLEncoder(new FileOutputStream("jbutton.xml"));
        e.writeObject(new JButton("Hello world"));
        e.close();  
XMLDecoder:
    XMLDecoder d = new XMLDecoder(new FileInputStream("jbutton.xml"));
        obj = d.readObject();
        d.close(); 
