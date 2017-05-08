//------------------------------------------------------------------------------
// <auto-generated>
//     Dieser Code wurde von einem Tool generiert.
//     Laufzeitversion:4.0.30319.42000
//
//     Änderungen an dieser Datei können falsches Verhalten verursachen und gehen verloren, wenn
//     der Code erneut generiert wird.
// </auto-generated>
//------------------------------------------------------------------------------

using NMF.Collections.Generic;
using NMF.Collections.ObjectModel;
using NMF.Expressions;
using NMF.Expressions.Linq;
using NMF.Models;
using NMF.Models.Collections;
using NMF.Models.Expressions;
using NMF.Models.Meta;
using NMF.Models.Repository;
using NMF.Serialization;
using NMF.Utilities;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Collections.Specialized;
using System.ComponentModel;
using System.Diagnostics;
using System.Linq;

namespace TTC2017.FamiliesToPersons.NMF.Persons
{
    
    
    /// <summary>
    /// The default implementation of the Person class
    /// </summary>
    [XmlIdentifierAttribute("name")]
    [XmlNamespaceAttribute("platform:/plugin/Persons/model/Persons.ecore")]
    [XmlNamespacePrefixAttribute("Persons")]
    [ModelRepresentationClassAttribute("platform:/plugin/Persons/model/Persons.ecore#//Person")]
    [DebuggerDisplayAttribute("Person {Name}")]
    public abstract partial class Person : ModelElement, IPerson, IModelElement
    {
        
        /// <summary>
        /// The backing field for the Name property
        /// </summary>
        private string _name;
        
        private static Lazy<ITypedElement> _nameAttribute = new Lazy<ITypedElement>(RetrieveNameAttribute);
        
        /// <summary>
        /// The backing field for the Birthday property
        /// </summary>
        private Nullable<DateTime> _birthday;
        
        private static Lazy<ITypedElement> _birthdayAttribute = new Lazy<ITypedElement>(RetrieveBirthdayAttribute);
        
        private static Lazy<ITypedElement> _personsInverseReference = new Lazy<ITypedElement>(RetrievePersonsInverseReference);
        
        private static IClass _classInstance;
        
        /// <summary>
        /// The name property
        /// </summary>
        [XmlElementNameAttribute("name")]
        [IdAttribute()]
        [XmlAttributeAttribute(true)]
        public string Name
        {
            get
            {
                return this._name;
            }
            set
            {
                if ((this._name != value))
                {
                    string old = this._name;
                    ValueChangedEventArgs e = new ValueChangedEventArgs(old, value);
                    this.OnNameChanging(e);
                    this.OnPropertyChanging("Name", e, _nameAttribute);
                    this._name = value;
                    this.OnNameChanged(e);
                    this.OnPropertyChanged("Name", e, _nameAttribute);
                }
            }
        }
        
        /// <summary>
        /// The birthday property
        /// </summary>
        [XmlElementNameAttribute("birthday")]
        [XmlAttributeAttribute(true)]
        [TypeConverter(typeof(IsoDateTimeConverter))]
        public Nullable<DateTime> Birthday
        {
            get
            {
                return this._birthday;
            }
            set
            {
                if ((this._birthday != value))
                {
                    Nullable<DateTime> old = this._birthday;
                    ValueChangedEventArgs e = new ValueChangedEventArgs(old, value);
                    this.OnBirthdayChanging(e);
                    this.OnPropertyChanging("Birthday", e, _birthdayAttribute);
                    this._birthday = value;
                    this.OnBirthdayChanged(e);
                    this.OnPropertyChanged("Birthday", e, _birthdayAttribute);
                }
            }
        }
        
        /// <summary>
        /// The personsInverse property
        /// </summary>
        [XmlElementNameAttribute("personsInverse")]
        [DesignerSerializationVisibilityAttribute(DesignerSerializationVisibility.Hidden)]
        [XmlAttributeAttribute(true)]
        [XmlOppositeAttribute("persons")]
        public IPersonRegister PersonsInverse
        {
            get
            {
                return ModelHelper.CastAs<IPersonRegister>(this.Parent);
            }
            set
            {
                this.Parent = value;
            }
        }
        
        /// <summary>
        /// Gets the referenced model elements of this model element
        /// </summary>
        public override IEnumerableExpression<IModelElement> ReferencedElements
        {
            get
            {
                return base.ReferencedElements.Concat(new PersonReferencedElementsCollection(this));
            }
        }
        
        /// <summary>
        /// Gets the Class model for this type
        /// </summary>
        public new static IClass ClassInstance
        {
            get
            {
                if ((_classInstance == null))
                {
                    _classInstance = ((IClass)(MetaRepository.Instance.Resolve("platform:/plugin/Persons/model/Persons.ecore#//Person")));
                }
                return _classInstance;
            }
        }
        
        /// <summary>
        /// Gets a value indicating whether the current model element can be identified by an attribute value
        /// </summary>
        public override bool IsIdentified
        {
            get
            {
                return true;
            }
        }
        
        /// <summary>
        /// Gets fired before the Name property changes its value
        /// </summary>
        public event System.EventHandler<ValueChangedEventArgs> NameChanging;
        
        /// <summary>
        /// Gets fired when the Name property changed its value
        /// </summary>
        public event System.EventHandler<ValueChangedEventArgs> NameChanged;
        
        /// <summary>
        /// Gets fired before the Birthday property changes its value
        /// </summary>
        public event System.EventHandler<ValueChangedEventArgs> BirthdayChanging;
        
        /// <summary>
        /// Gets fired when the Birthday property changed its value
        /// </summary>
        public event System.EventHandler<ValueChangedEventArgs> BirthdayChanged;
        
        /// <summary>
        /// Gets fired before the PersonsInverse property changes its value
        /// </summary>
        public event System.EventHandler<ValueChangedEventArgs> PersonsInverseChanging;
        
        /// <summary>
        /// Gets fired when the PersonsInverse property changed its value
        /// </summary>
        public event System.EventHandler<ValueChangedEventArgs> PersonsInverseChanged;
        
        private static ITypedElement RetrieveNameAttribute()
        {
            return ((ITypedElement)(((ModelElement)(TTC2017.FamiliesToPersons.NMF.Persons.Person.ClassInstance)).Resolve("name")));
        }
        
        /// <summary>
        /// Raises the NameChanging event
        /// </summary>
        /// <param name="eventArgs">The event data</param>
        protected virtual void OnNameChanging(ValueChangedEventArgs eventArgs)
        {
            System.EventHandler<ValueChangedEventArgs> handler = this.NameChanging;
            if ((handler != null))
            {
                handler.Invoke(this, eventArgs);
            }
        }
        
        /// <summary>
        /// Raises the NameChanged event
        /// </summary>
        /// <param name="eventArgs">The event data</param>
        protected virtual void OnNameChanged(ValueChangedEventArgs eventArgs)
        {
            System.EventHandler<ValueChangedEventArgs> handler = this.NameChanged;
            if ((handler != null))
            {
                handler.Invoke(this, eventArgs);
            }
        }
        
        private static ITypedElement RetrieveBirthdayAttribute()
        {
            return ((ITypedElement)(((ModelElement)(TTC2017.FamiliesToPersons.NMF.Persons.Person.ClassInstance)).Resolve("birthday")));
        }
        
        /// <summary>
        /// Raises the BirthdayChanging event
        /// </summary>
        /// <param name="eventArgs">The event data</param>
        protected virtual void OnBirthdayChanging(ValueChangedEventArgs eventArgs)
        {
            System.EventHandler<ValueChangedEventArgs> handler = this.BirthdayChanging;
            if ((handler != null))
            {
                handler.Invoke(this, eventArgs);
            }
        }
        
        /// <summary>
        /// Raises the BirthdayChanged event
        /// </summary>
        /// <param name="eventArgs">The event data</param>
        protected virtual void OnBirthdayChanged(ValueChangedEventArgs eventArgs)
        {
            System.EventHandler<ValueChangedEventArgs> handler = this.BirthdayChanged;
            if ((handler != null))
            {
                handler.Invoke(this, eventArgs);
            }
        }
        
        private static ITypedElement RetrievePersonsInverseReference()
        {
            return ((ITypedElement)(((ModelElement)(TTC2017.FamiliesToPersons.NMF.Persons.Person.ClassInstance)).Resolve("personsInverse")));
        }
        
        /// <summary>
        /// Raises the PersonsInverseChanging event
        /// </summary>
        /// <param name="eventArgs">The event data</param>
        protected virtual void OnPersonsInverseChanging(ValueChangedEventArgs eventArgs)
        {
            System.EventHandler<ValueChangedEventArgs> handler = this.PersonsInverseChanging;
            if ((handler != null))
            {
                handler.Invoke(this, eventArgs);
            }
        }
        
        /// <summary>
        /// Gets called when the parent model element of the current model element is about to change
        /// </summary>
        /// <param name="oldParent">The old parent model element</param>
        /// <param name="newParent">The new parent model element</param>
        protected override void OnParentChanging(IModelElement newParent, IModelElement oldParent)
        {
            IPersonRegister oldPersonsInverse = ModelHelper.CastAs<IPersonRegister>(oldParent);
            IPersonRegister newPersonsInverse = ModelHelper.CastAs<IPersonRegister>(newParent);
            ValueChangedEventArgs e = new ValueChangedEventArgs(oldPersonsInverse, newPersonsInverse);
            this.OnPersonsInverseChanging(e);
            this.OnPropertyChanging("PersonsInverse", e, _personsInverseReference);
        }
        
        /// <summary>
        /// Raises the PersonsInverseChanged event
        /// </summary>
        /// <param name="eventArgs">The event data</param>
        protected virtual void OnPersonsInverseChanged(ValueChangedEventArgs eventArgs)
        {
            System.EventHandler<ValueChangedEventArgs> handler = this.PersonsInverseChanged;
            if ((handler != null))
            {
                handler.Invoke(this, eventArgs);
            }
        }
        
        /// <summary>
        /// Gets called when the parent model element of the current model element changes
        /// </summary>
        /// <param name="oldParent">The old parent model element</param>
        /// <param name="newParent">The new parent model element</param>
        protected override void OnParentChanged(IModelElement newParent, IModelElement oldParent)
        {
            IPersonRegister oldPersonsInverse = ModelHelper.CastAs<IPersonRegister>(oldParent);
            IPersonRegister newPersonsInverse = ModelHelper.CastAs<IPersonRegister>(newParent);
            if ((oldPersonsInverse != null))
            {
                oldPersonsInverse.Persons.Remove(this);
            }
            if ((newPersonsInverse != null))
            {
                newPersonsInverse.Persons.Add(this);
            }
            ValueChangedEventArgs e = new ValueChangedEventArgs(oldPersonsInverse, newPersonsInverse);
            this.OnPersonsInverseChanged(e);
            this.OnPropertyChanged("PersonsInverse", e, _personsInverseReference);
            base.OnParentChanged(newParent, oldParent);
        }
        
        /// <summary>
        /// Resolves the given attribute name
        /// </summary>
        /// <returns>The attribute value or null if it could not be found</returns>
        /// <param name="attribute">The requested attribute name</param>
        /// <param name="index">The index of this attribute</param>
        protected override object GetAttributeValue(string attribute, int index)
        {
            if ((attribute == "NAME"))
            {
                return this.Name;
            }
            if ((attribute == "BIRTHDAY"))
            {
                return this.Birthday;
            }
            return base.GetAttributeValue(attribute, index);
        }
        
        /// <summary>
        /// Sets a value to the given feature
        /// </summary>
        /// <param name="feature">The requested feature</param>
        /// <param name="value">The value that should be set to that feature</param>
        protected override void SetFeature(string feature, object value)
        {
            if ((feature == "PERSONSINVERSE"))
            {
                this.PersonsInverse = ((IPersonRegister)(value));
                return;
            }
            if ((feature == "NAME"))
            {
                this.Name = ((string)(value));
                return;
            }
            if ((feature == "BIRTHDAY"))
            {
                this.Birthday = ((DateTime)(value));
                return;
            }
            base.SetFeature(feature, value);
        }
        
        /// <summary>
        /// Gets the property expression for the given attribute
        /// </summary>
        /// <returns>An incremental property expression</returns>
        /// <param name="attribute">The requested attribute in upper case</param>
        protected override INotifyExpression<object> GetExpressionForAttribute(string attribute)
        {
            if ((attribute == "PersonsInverse"))
            {
                return new PersonsInverseProxy(this);
            }
            return base.GetExpressionForAttribute(attribute);
        }
        
        /// <summary>
        /// Gets the property expression for the given reference
        /// </summary>
        /// <returns>An incremental property expression</returns>
        /// <param name="reference">The requested reference in upper case</param>
        protected override INotifyExpression<IModelElement> GetExpressionForReference(string reference)
        {
            if ((reference == "PersonsInverse"))
            {
                return new PersonsInverseProxy(this);
            }
            return base.GetExpressionForReference(reference);
        }
        
        /// <summary>
        /// Gets the Class for this model element
        /// </summary>
        public override IClass GetClass()
        {
            if ((_classInstance == null))
            {
                _classInstance = ((IClass)(MetaRepository.Instance.Resolve("platform:/plugin/Persons/model/Persons.ecore#//Person")));
            }
            return _classInstance;
        }
        
        /// <summary>
        /// Gets the identifier string for this model element
        /// </summary>
        /// <returns>The identifier string</returns>
        public override string ToIdentifierString()
        {
            if ((this.Name == null))
            {
                return null;
            }
            return this.Name.ToString();
        }
        
        /// <summary>
        /// The collection class to to represent the children of the Person class
        /// </summary>
        public class PersonReferencedElementsCollection : ReferenceCollection, ICollectionExpression<IModelElement>, ICollection<IModelElement>
        {
            
            private Person _parent;
            
            /// <summary>
            /// Creates a new instance
            /// </summary>
            public PersonReferencedElementsCollection(Person parent)
            {
                this._parent = parent;
            }
            
            /// <summary>
            /// Gets the amount of elements contained in this collection
            /// </summary>
            public override int Count
            {
                get
                {
                    int count = 0;
                    if ((this._parent.PersonsInverse != null))
                    {
                        count = (count + 1);
                    }
                    return count;
                }
            }
            
            protected override void AttachCore()
            {
                this._parent.PersonsInverseChanged += this.PropagateValueChanges;
            }
            
            protected override void DetachCore()
            {
                this._parent.PersonsInverseChanged -= this.PropagateValueChanges;
            }
            
            /// <summary>
            /// Adds the given element to the collection
            /// </summary>
            /// <param name="item">The item to add</param>
            public override void Add(IModelElement item)
            {
                if ((this._parent.PersonsInverse == null))
                {
                    IPersonRegister personsInverseCasted = item.As<IPersonRegister>();
                    if ((personsInverseCasted != null))
                    {
                        this._parent.PersonsInverse = personsInverseCasted;
                        return;
                    }
                }
            }
            
            /// <summary>
            /// Clears the collection and resets all references that implement it.
            /// </summary>
            public override void Clear()
            {
                this._parent.PersonsInverse = null;
            }
            
            /// <summary>
            /// Gets a value indicating whether the given element is contained in the collection
            /// </summary>
            /// <returns>True, if it is contained, otherwise False</returns>
            /// <param name="item">The item that should be looked out for</param>
            public override bool Contains(IModelElement item)
            {
                if ((item == this._parent.PersonsInverse))
                {
                    return true;
                }
                return false;
            }
            
            /// <summary>
            /// Copies the contents of the collection to the given array starting from the given array index
            /// </summary>
            /// <param name="array">The array in which the elements should be copied</param>
            /// <param name="arrayIndex">The starting index</param>
            public override void CopyTo(IModelElement[] array, int arrayIndex)
            {
                if ((this._parent.PersonsInverse != null))
                {
                    array[arrayIndex] = this._parent.PersonsInverse;
                    arrayIndex = (arrayIndex + 1);
                }
            }
            
            /// <summary>
            /// Removes the given item from the collection
            /// </summary>
            /// <returns>True, if the item was removed, otherwise False</returns>
            /// <param name="item">The item that should be removed</param>
            public override bool Remove(IModelElement item)
            {
                if ((this._parent.PersonsInverse == item))
                {
                    this._parent.PersonsInverse = null;
                    return true;
                }
                return false;
            }
            
            /// <summary>
            /// Gets an enumerator that enumerates the collection
            /// </summary>
            /// <returns>A generic enumerator</returns>
            public override IEnumerator<IModelElement> GetEnumerator()
            {
                return Enumerable.Empty<IModelElement>().Concat(this._parent.PersonsInverse).GetEnumerator();
            }
        }
        
        /// <summary>
        /// Represents a proxy to represent an incremental access to the name property
        /// </summary>
        private sealed class NameProxy : ModelPropertyChange<IPerson, string>
        {
            
            /// <summary>
            /// Creates a new observable property access proxy
            /// </summary>
            /// <param name="modelElement">The model instance element for which to create the property access proxy</param>
            public NameProxy(IPerson modelElement) : 
                    base(modelElement, "name")
            {
            }
            
            /// <summary>
            /// Gets or sets the value of this expression
            /// </summary>
            public override string Value
            {
                get
                {
                    return this.ModelElement.Name;
                }
                set
                {
                    this.ModelElement.Name = value;
                }
            }
        }
        
        /// <summary>
        /// Represents a proxy to represent an incremental access to the birthday property
        /// </summary>
        private sealed class BirthdayProxy : ModelPropertyChange<IPerson, Nullable<DateTime>>
        {
            
            /// <summary>
            /// Creates a new observable property access proxy
            /// </summary>
            /// <param name="modelElement">The model instance element for which to create the property access proxy</param>
            public BirthdayProxy(IPerson modelElement) : 
                    base(modelElement, "birthday")
            {
            }
            
            /// <summary>
            /// Gets or sets the value of this expression
            /// </summary>
            public override Nullable<DateTime> Value
            {
                get
                {
                    return this.ModelElement.Birthday;
                }
                set
                {
                    this.ModelElement.Birthday = value;
                }
            }
        }
        
        /// <summary>
        /// Represents a proxy to represent an incremental access to the personsInverse property
        /// </summary>
        private sealed class PersonsInverseProxy : ModelPropertyChange<IPerson, IPersonRegister>
        {
            
            /// <summary>
            /// Creates a new observable property access proxy
            /// </summary>
            /// <param name="modelElement">The model instance element for which to create the property access proxy</param>
            public PersonsInverseProxy(IPerson modelElement) : 
                    base(modelElement, "personsInverse")
            {
            }
            
            /// <summary>
            /// Gets or sets the value of this expression
            /// </summary>
            public override IPersonRegister Value
            {
                get
                {
                    return this.ModelElement.PersonsInverse;
                }
                set
                {
                    this.ModelElement.PersonsInverse = value;
                }
            }
        }
    }
}

