package org.soc.gwt.client.wiki;

import org.soc.common.core.OpenZettlers.OsModel;

/**
 * 
 */
public class PageRenders {
  public interface WikiRender {
    /** A link displaying an icon, name of model linking to wiki page of model */
    public StringBuilder link(OsModel model);
    /** A default template wikipage for the model */
    public StringBuilder pageTemplate(OsModel model);
  }

  public class DefaultWikiRender implements WikiRender {
    @Override public StringBuilder link(OsModel model) {
      return WikiBuilder.start()
              .image(model.name().value(), 16)
              .link()
              .build();
    }
    @Override public StringBuilder pageTemplate(OsModel model) {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public class ModelOverView implements WikiRender {
    @Override public StringBuilder link(OsModel model) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public StringBuilder pageTemplate(OsModel model) {
      // h1: All supported models by type
      // for lists: h2 {listName}
      //   for model: row
      //     name
      //     description
      //     icon16/24/32/48
      //     
      return null;
    }
  }

  public static class WikiBuilder {
    StringBuilder sb = new StringBuilder();

    public StringBuilder build() {
      return sb;
    }
    public static WikiBuilder start() {
      return new WikiBuilder();
    }
    public WikiBuilder h1(String h1) {
      sb.append("= ")
              .append(h1)
              .append(" =");
      return this;
    }
    public WikiBuilder body(String body) {
      sb.append(body);
      return this;
    }
    public WikiBuilder list(String listItem) {
      sb.append("* ");
      return this;
    }
    public WikiBuilder image(String modelName, int size) {
      sb.append("http://opensettlers.github.com/images/size")
              .append(size)
              .append("/")
              .append(modelName)
              .append(".png");
      return this;
    }
    public WikiBuilder link() {
      return this;
    }
  }
}
