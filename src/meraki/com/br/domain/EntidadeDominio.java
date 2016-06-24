package meraki.com.br.domain;

import java.util.Calendar;

/**
*
* @author Vitor Sakassegawa
*/
public class EntidadeDominio implements IEntidade
{
  Integer id;
  private Calendar date;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Calendar getDate()
    {
        return date;
    }

    public void setDate(Calendar date)
    {
        this.date = date;
    }
}