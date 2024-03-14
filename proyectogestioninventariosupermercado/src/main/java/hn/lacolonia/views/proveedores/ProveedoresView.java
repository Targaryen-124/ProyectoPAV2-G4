package hn.lacolonia.views.proveedores;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;

import hn.lacolonia.controller.InteractorImplProveedores;
import hn.lacolonia.controller.InteractorProveedores;
import hn.lacolonia.data.Proveedor;
import hn.lacolonia.views.MainLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@PageTitle("Proveedores")
@Route(value = "proveedores/:identificacion?/:action?(edit)", layout = MainLayout.class)
//@RouteAlias(value = "", layout = MainLayout.class)
//@Uses(Icon.class)
public class ProveedoresView extends Div implements BeforeEnterObserver, ViewModelProveedores {

    private final String SUPPLIER_IDENTIFICACION = "identificacion";
    private final String SUPPLIER_EDIT_ROUTE_TEMPLATE = "proveedores/%s/edit";

    private final Grid<Proveedor> grid = new Grid<>(Proveedor.class, false);

    private TextField identificacion;
    private TextField nombre;
    private TextField direccion;
    private TextField telefono;
    private TextField productos;

    private final Button cancel = new Button("Cancelar", new Icon(VaadinIcon.CLOSE_CIRCLE));
    private final Button save = new Button("Guardar", new Icon(VaadinIcon.CHECK_CIRCLE));
    private final Button eliminar = new Button("Eliminar", new Icon(VaadinIcon.TRASH));
    private final Button consultar = new Button("Consultar", new Icon(VaadinIcon.SEARCH));

    private Proveedor proveedorSeleccionado;
    private List<Proveedor> elementos;
    private InteractorProveedores controlador;

    public ProveedoresView() {
        addClassNames("proveedores-view");

        controlador = new InteractorImplProveedores(this);
        elementos = new ArrayList<>();
        
        // Create UI
        SplitLayout splitLayout = new SplitLayout();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid // SE PUEDE CONFIGURAR EL GRID CAMBIANDO DE POSICION LOS CAMPOS
        grid.addColumn("identificacion").setAutoWidth(true);
        grid.addColumn("nombre").setAutoWidth(true);
        grid.addColumn("direccion").setAutoWidth(true);
        grid.addColumn("telefono").setAutoWidth(true);
        grid.addColumn("productos").setAutoWidth(true);
        
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(SUPPLIER_EDIT_ROUTE_TEMPLATE, event.getValue().getIdentificacion()));
            } else {
                clearForm();
                UI.getCurrent().navigate(ProveedoresView.class);
            }
        });
        
        controlador.consultarProveedores();

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.proveedorSeleccionado == null) {
                    this.proveedorSeleccionado = new Proveedor();
                }

                clearForm();
                refreshGrid();
                Notification.show("Data updated");
                UI.getCurrent().navigate(ProveedoresView.class);
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                        "Error updating the data. Somebody else has updated the record while you were making changes.");
                n.setPosition(Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
        
        eliminar.addClickListener(e -> {
        	Notification n = Notification.show("Boton Eliminar Seleccionado, Aun no hay Nada que Eliminar");
        	n.setPosition(Position.MIDDLE);
            n.addThemeVariants(NotificationVariant.LUMO_WARNING);
        });
        
        consultar.addClickListener(e -> {
        	Notification n = Notification.show("Consultando Proveedores");
        	n.setPosition(Position.MIDDLE);
            n.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        });
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<String> identificacionProveedor = event.getRouteParameters().get(SUPPLIER_IDENTIFICACION);
        if (identificacionProveedor.isPresent()) {
            Proveedor proveedorObtenido = obtenerProveedor(identificacionProveedor.get());
            if (proveedorObtenido != null) {
                populateForm(proveedorObtenido);
            } else {
                Notification.show(
                        String.format("El proveedor con identificacion = %s no existe", identificacionProveedor.get()), 3000,
                        Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(ProveedoresView.class);
            }
        }
    }

    private Proveedor obtenerProveedor(String identificacion) {
		Proveedor provencontrado = null;
		for(Proveedor prov: elementos) {
			if(prov.getIdentificacion().equals(identificacion)) {
				provencontrado = prov;
				break;
			}
			
		}
		return provencontrado;
	}

	private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setClassName("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        identificacion = new TextField("Identificacion");
        identificacion.setId("txt_identificacion");
        identificacion.setPrefixComponent(VaadinIcon.ELLIPSIS_DOTS_H.create());
        
        nombre = new TextField("Nombre");
        nombre.setId("txt_nombre");
        nombre.setPrefixComponent(VaadinIcon.TRUCK.create());
        
        direccion = new TextField("Direccion");
        direccion.setId("txt_direccion");
        direccion.setPrefixComponent(VaadinIcon.MAP_MARKER.create());
        
        telefono = new TextField("Telefono");
        telefono.setId("txt_telefono");
        telefono.setPrefixComponent(VaadinIcon.PHONE.create());
        
        productos = new TextField("Productos Suministrados");
        productos.setId("txt_productos");
        productos.setPrefixComponent(VaadinIcon.CART.create());
        
        // METODO ADD
        formLayout.add(identificacion, nombre, direccion, telefono, productos);

        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        cancel.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        cancel.setId("btn_cancelar");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.setId("btn_guardar");
        eliminar.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        eliminar.setId("btn_eliminar");
        consultar.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        consultar.setId("btn_consultar");
        
        buttonLayout.add(save, cancel, eliminar, consultar);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setClassName("grid-wrapper");
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Proveedor value) {
        this.proveedorSeleccionado = value;
        if(value != null) {
        	identificacion.setValue(value.getIdentificacion());
            nombre.setValue(value.getNombre());
            direccion.setValue(value.getDireccion());
            telefono.setValue(value.getTelefono());
            productos.setValue(value.getProductos());
        }else {
        	identificacion.setValue("");
            nombre.setValue("");
            direccion.setValue("");
            telefono.setValue("");
            productos.setValue("");
        }
    }

	@Override
	public void mostrarProveedoresEnGrid(List<Proveedor> items) {
		Collection<Proveedor> itemsCollection = items;
		grid.setItems(itemsCollection);
		this.elementos = items;
	}

	@Override
	public void mostrarMensajeError(String mensaje) {
		Notification.show(mensaje);
	}
}
