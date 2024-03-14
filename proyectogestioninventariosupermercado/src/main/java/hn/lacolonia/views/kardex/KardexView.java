package hn.lacolonia.views.kardex;

import java.util.List;
import java.util.Optional;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import hn.lacolonia.controller.InteractorProductos;
import hn.lacolonia.data.Kardex;
//import hn.lacolonia.data.Producto;
import hn.lacolonia.views.MainLayout;
import hn.lacolonia.views.productos.ProductosView;
import hn.lacolonia.views.productos.ViewModelProductos;

@PageTitle("Kardex")
@Route(value = "kardex", layout = MainLayout.class)
@Uses(Icon.class)
public class KardexView extends /*Div implements*/ Composite<VerticalLayout> /*BeforeEnterObserver, ViewModelProductos*/ {
	private final String KARDEX_IDENTIFICACION = "identificacion";
    private final String KARDEX_EDIT_ROUTE_TEMPLATE = "kardex/%s/edit";
    
    public KardexView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        FormLayout formLayout2Col = new FormLayout();
        TextField identificacion = new TextField();
        TextField codigoProducto = new TextField();
        DatePicker fechaEntradaSalidaProducto = new DatePicker();
        ComboBox<String> transaccion = new ComboBox<String>();
        IntegerField cantidad = new IntegerField();
        IntegerField existencia = new IntegerField();
        HorizontalLayout layoutRow = new HorizontalLayout();
        Button crear = new Button("Crear", new Icon(VaadinIcon.PENCIL));
        Button cancelar = new Button("Cancelar", new Icon(VaadinIcon.CLOSE_CIRCLE));
        Button consultar = new Button("Consultar", new Icon(VaadinIcon.SEARCH));
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        h3.setText("Kardex");
        h3.setWidth("100%");
        formLayout2Col.setWidth("100%");
        
        /*private Kardex kardexSeleccionado;
        private List<Kardex> elementos;
        private InteractorKardex controlador;*/
        
        identificacion.setLabel("Identificacion");
        identificacion.setId("txt_identificacion");
        identificacion.setPrefixComponent(VaadinIcon.ELLIPSIS_DOTS_H.create());
        
        codigoProducto.setLabel("Codigo del Producto");
        codigoProducto.setId("txt_codigoProducto");
        codigoProducto.setPrefixComponent(VaadinIcon.BARCODE.create());
        
        fechaEntradaSalidaProducto.setLabel("Fecha de Entrada o Salida del Producto");
        fechaEntradaSalidaProducto.setId("txt_fechaEntradaSalidaProducto");
        
        transaccion.setLabel("Transaccion");
        transaccion.setId("cbo_transaccion");
        transaccion.setAllowCustomValue(true);
        transaccion.setItems("Entrada", "Salida");
        transaccion.setHelperText("Seleccione el Tipo de Transaccion");
        
        cantidad.setLabel("Cantidad");
        cantidad.setId("txt_cantidad");
        cantidad.setPrefixComponent(VaadinIcon.CLIPBOARD_TEXT.create());
        
        existencia.setLabel("Existencia");
        existencia.setId("txt_existencia");
        existencia.setMin(0);
        existencia.setMax(1000);
        existencia.setValue(0);
        existencia.setStepButtonsVisible(true);
        
        consultar.addClickListener(e -> {
        	Notification n = Notification.show("Consultando el Kardex");
        	n.setPosition(Position.MIDDLE);
            n.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        });
        
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        crear.setText("Crear");
        crear.setId("btn_crear");
        crear.setWidth("min-content");
        crear.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancelar.setText("Cancelar");
        cancelar.setId("btn_cancelar");
        cancelar.setWidth("min-content");
        cancelar.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        consultar.setText("Consultar");
        consultar.setId("btn_consultar");
        cancelar.setWidth("min-content");
        consultar.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        getContent().add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(identificacion);
        formLayout2Col.add(codigoProducto);
        formLayout2Col.add(fechaEntradaSalidaProducto);
        formLayout2Col.add(transaccion);
        formLayout2Col.add(cantidad);
        formLayout2Col.add(existencia);
        layoutColumn2.add(layoutRow);
        layoutRow.add(crear);
        layoutRow.add(cancelar);
        layoutRow.add(consultar);
    }

	/*@Override
	public void beforeEnter(BeforeEnterEvent event) {
		Optional<String> identificacionKardex = event.getRouteParameters().get(KARDEX_IDENTIFICACION);
        if (identificacionKardex.isPresent()) {
            Kardex kardexObtenido = obtenerKardex(identificacionKardex.get());
            if (kardexObtenido != null) {
                populateForm(kardexObtenido);
            } else {
                Notification.show(
                        String.format("El Kardex con identificacion = %s no existe", identificacionKardex.get()), 3000,
                        Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                //refreshGrid();
                event.forwardTo(ProductosView.class);
            }
        }
    }

	@Override
	public void mostrarProductosEnGrid(List<Producto> items) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarMensajeError(String mensaje) {
		// TODO Auto-generated method stub
		
	}*/
}
